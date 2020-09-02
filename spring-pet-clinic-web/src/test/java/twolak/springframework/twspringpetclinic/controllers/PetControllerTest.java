package twolak.springframework.twspringpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import twolak.springframework.twspringpetclinic.config.ControllerGlobals;
import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
	
	private static final String VIEWS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

	@Mock
	private PetService petService;
	
	@Mock
	private OwnerService ownerService;
	
	@Mock
	private PetTypeService petTypeService;
	
	@InjectMocks
	private PetController petController;
	
	private MockMvc mockMvc;
	
	private Owner owner;
	private Set<PetType> petTypes;
	

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.petController).build();
		
		this.owner = Owner.builder().id(1L).pets(new HashSet<>()).build();
		this.petTypes = new HashSet<>();
		this.petTypes.add(PetType.builder().id(1L).name("Dog").build());
		this.petTypes.add(PetType.builder().id(2L).name("Cat").build());
	}

	@Test
	void testInitCreationForm() throws Exception {
		when(this.ownerService.findById(anyLong())).thenReturn(this.owner);
		when(this.petTypeService.findAll()).thenReturn(this.petTypes);
		
		this.mockMvc.perform(get("/owners/1/pets/new"))
			.andExpect(status().isOk())
			.andExpect(view().name(VIEWS_CREATE_OR_UPDATE_PET_FORM))
			.andExpect(model().attribute("owner", notNullValue(Owner.class)))
			.andExpect(model().attributeExists("petTypes"))
			.andExpect(model().attribute("pet", notNullValue(Pet.class)));
		verify(this.ownerService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerService);
		verify(this.petTypeService, times(1)).findAll();
		verifyNoMoreInteractions(this.petTypeService);
	}
	
	@Test
	void testProcessCreationForm() throws Exception {
		when(this.ownerService.findById(anyLong())).thenReturn(this.owner);
		when(this.petTypeService.findAll()).thenReturn(this.petTypes);
		when(this.petService.save(any())).thenReturn(Pet.builder().id(1L).name("Brutus").petType(this.petTypes.iterator().next()).build());
		
		this.mockMvc.perform(post("/owners/1/pets/new"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(ControllerGlobals.REDIRECT + "/owners/1"))
			.andExpect(model().attribute("owner", notNullValue(Owner.class)))
			.andExpect(model().attribute("petTypes", notNullValue(PetType.class)));
		verify(this.ownerService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerService);
		verify(this.petTypeService, times(1)).findAll();
		verifyNoMoreInteractions(this.petTypeService);
		verify(this.petService, times(1)).save(any());
		verifyNoMoreInteractions(this.petService);
	}
	
	@Test
	void testInitUpdateForm() throws Exception {
		when(this.ownerService.findById(anyLong())).thenReturn(this.owner);
		when(this.petTypeService.findAll()).thenReturn(this.petTypes);
		when(this.petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).name("Brutus").petType(this.petTypes.iterator().next()).build());
		
		this.mockMvc.perform(get("/owners/1/pets/1/edit"))
			.andExpect(status().isOk())
			.andExpect(view().name(VIEWS_CREATE_OR_UPDATE_PET_FORM))
			.andExpect(model().attribute("owner", notNullValue(Owner.class)))
			.andExpect(model().attribute("petTypes", notNullValue(PetType.class)))
			.andExpect(model().attribute("pet", notNullValue(Pet.class)));
		verify(this.ownerService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerService);
		verify(this.petTypeService, times(1)).findAll();
		verifyNoMoreInteractions(this.petTypeService);
		verify(this.petService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.petService);
	}
	
	@Test
	void testProcessUpdateForm() throws Exception {
		when(this.ownerService.findById(anyLong())).thenReturn(this.owner);
		when(this.petTypeService.findAll()).thenReturn(this.petTypes);
//		when(this.petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).name("Brutus").petType(this.petTypes.iterator().next()).build());
		
		this.mockMvc.perform(post("/owners/1/pets/1/edit"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(ControllerGlobals.REDIRECT + "/owners/1"))
			.andExpect(model().attribute("owner", notNullValue(Owner.class)))
			.andExpect(model().attribute("petTypes", notNullValue(PetType.class)));
		verify(this.ownerService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerService);
		verify(this.petTypeService, times(1)).findAll();
		verifyNoMoreInteractions(this.petTypeService);
		verify(this.petService, times(1)).save(any());
		verifyNoMoreInteractions(this.petService);
	}

}
