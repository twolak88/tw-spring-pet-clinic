/**
 * 
 */
package twolak.springframework.twspringpetclinic.controllers;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import twolak.springframework.twspringpetclinic.config.ControllerGlobals;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.Visit;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.VisitService;

/**
 * @author twolak
 *
 */
@Controller
@RequestMapping("/owners/*/pets/{petId}/visits/new")
public class VisitController {
	
	private static final String VISIT_ATTR_NAME = "visit";
	private static final String PET_ATTR_NAME = "pet";
	private static final String VIEWS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
	private final VisitService visitService;
	private final PetService petService;
	
	public VisitController(VisitService visitService, PetService petService) {
		this.visitService = visitService;
		this.petService = petService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
		
		webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text));
			}
		});
	}
	
	//run before each mapped methods
	@ModelAttribute(VISIT_ATTR_NAME)
	public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
		Pet pet = this.petService.findById(petId);
		model.addAttribute(PET_ATTR_NAME, pet);
		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}
	
	@GetMapping
	public String initNewVisitForm(@PathVariable Long petId, Model model) {
		return VIEWS_CREATE_OR_UPDATE_VISIT_FORM;
	}
	
	@PostMapping
	public String processNewVisitForm(@Valid Visit visit, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return VIEWS_CREATE_OR_UPDATE_VISIT_FORM;
		}
		this.visitService.save(visit);
		return ControllerGlobals.REDIRECT + "/owners/" + visit.getPet().getOwner().getId();
	}
}
