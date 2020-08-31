package twolak.springframework.twspringpetclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.services.OwnerService;

/**
 * @author twolak
 *
 */
@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {
	
	@Mock
	private Model model;
	
	@Mock
	private OwnerService ownerService;
	
	@InjectMocks
	private OwnerController ownerController;
	
	private Set<Owner> owners;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		this.owners = new HashSet<>();
		this.owners.add(Owner.builder().id(1L).build());
		this.owners.add(Owner.builder().id(2L).build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}

	@Test
	void testFindOwners() throws Exception {
		this.mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"))
			.andExpect(model().attributeExists("owner"));
		
		verifyNoInteractions(this.ownerService);
	}
	
	@Test
	void testProcessFindFormReturnMany() throws Exception {
		when(this.ownerService.findAllByLastNameLike(anyString())).thenReturn(this.owners);
		
		this.mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownersList"))
			.andExpect(model().attribute("owners", hasSize(2)));
		verify(this.ownerService, times(1)).findAllByLastNameLike(anyString());
		verifyNoMoreInteractions(this.ownerService);
	}
	
	@Test
	void testProcessFindFormReturnOne() throws Exception {
		when(this.ownerService.findAllByLastNameLike(anyString())).thenReturn(Stream.of(Owner.builder().id(1L).build()).collect(Collectors.toSet()));
		
		this.mockMvc.perform(get("/owners"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/1"));
		verify(this.ownerService, times(1)).findAllByLastNameLike(anyString());
		verifyNoMoreInteractions(this.ownerService);
	}
	
	@Test
	void testProcessFindFormReturnNone() throws Exception {
		when(this.ownerService.findAllByLastNameLike(anyString())).thenReturn(new HashSet<>());
		
		this.mockMvc.perform(get("/owners"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/findOwners"));
		verify(this.ownerService, times(1)).findAllByLastNameLike(anyString());
		verifyNoMoreInteractions(this.ownerService);
		
	}
	
	@Test
	void testShowOwner() throws Exception {
		when(this.ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
		
		this.mockMvc.perform(get("/owners/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("owners/ownerDetails"))
			.andExpect(model().attribute("owner", hasProperty("id", is(1L))));
		verify(this.ownerService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerService);
	}
}
