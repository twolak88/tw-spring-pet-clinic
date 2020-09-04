package twolak.springframework.twspringpetclinic.controllers;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import twolak.springframework.twspringpetclinic.config.ControllerGlobals;
import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.model.Visit;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.VisitService;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
	
	private static final String SOME_VISIT_DESCRIPTION = "some visit description";
	private static final String VIEWS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
	private final Long ownerId = 1L;
	private final Long petId = 1L;
	
	@Mock
	private VisitService visitService;
	
	@Mock
	private PetService petService;
	
	@InjectMocks
	private VisitController visitController;
	
	private MockMvc mockMvc;
	
	private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
	private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;
    
    private Pet pet;
	
	@BeforeEach
	void setUp() throws Exception {
		pet = Pet.builder().id(petId)
								.birthDate(LocalDate.of(2020, 1, 1))
								.name("Mike")
								.visits(new HashSet<>())
								.owner(Owner.builder().id(ownerId)
										.lastName("Down")
										.firstName("Luke")
										.build())
								.petType(PetType.builder().name("Dog").build())
								.build();
		when(this.petService.findById(anyLong())).thenReturn(pet);
		uriVariables.clear();
		uriVariables.put("ownerId", ownerId.toString());
		uriVariables.put("petId", petId.toString());
		visitsUri = visitsUriTemplate.expand(uriVariables);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.visitController).build();
	}

	@Test
	void testInitNewVisitForm() throws Exception {
		this.mockMvc.perform(get(visitsUri))
			.andExpect(status().isOk())
			.andExpect(view().name(VIEWS_CREATE_OR_UPDATE_VISIT_FORM))
			.andExpect(model().attribute("visit", notNullValue(Visit.class)));
		verify(this.petService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.petService);
	}
	
	@Test
	void testProcessNewVisitForm() throws Exception {
		when(this.visitService.save(any())).thenReturn(Visit.builder()
				.date(LocalDate.of(2020, 9, 1))
				.description(SOME_VISIT_DESCRIPTION)
				.pet(pet).build());
		this.mockMvc.perform(post(visitsUri)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("date", "2018-11-11")
				.param("description", SOME_VISIT_DESCRIPTION))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(ControllerGlobals.REDIRECT + "/owners/" + ownerId))
			.andExpect(model().attribute("visit", notNullValue(Visit.class)));
		verify(this.petService, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.petService);
		verify(this.visitService, times(1)).save(any());
		verifyNoMoreInteractions(this.visitService);
	}
}
