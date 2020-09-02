package twolak.springframework.twspringpetclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import twolak.springframework.twspringpetclinic.config.ControllerGlobals;
import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {
	
	private static final String VIEWS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
	
	private static final String PET_ATTR_NAME = "pet";
	private static final String OWNER_ATTR_NAME = "owner";
	private static final String PET_TYPES_ATTR_NAME = "petTypes";
	
	private final OwnerService ownerService;
	private final PetService petService;
	private final PetTypeService petTypeService;
	
	public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.petService = petService;
		this.petTypeService = petTypeService;
	}
	
	@ModelAttribute(PET_TYPES_ATTR_NAME)
	public Collection<PetType> populatePetTypes() {
		return this.petTypeService.findAll();
	}
	
	@ModelAttribute(OWNER_ATTR_NAME)
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return this.ownerService.findById(ownerId);
	}
	
	@InitBinder(OWNER_ATTR_NAME)
	public void initOwnerBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}
	
	@GetMapping("/new")
	public String initCreationForm(Owner owner, Model model) {
		Pet pet = Pet.builder().build();
		owner.addPet(pet);
		model.addAttribute(PET_ATTR_NAME, pet);
		return VIEWS_CREATE_OR_UPDATE_PET_FORM;
	}
	
	@PostMapping("/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			bindingResult.rejectValue("name", "duplicate", "already exists");
		}
		owner.addPet(pet);
		if (bindingResult.hasErrors()) {
			model.addAttribute(PET_ATTR_NAME, pet);
			return VIEWS_CREATE_OR_UPDATE_PET_FORM;
		}
		this.petService.save(pet);
		return ControllerGlobals.REDIRECT + "/owners/" + owner.getId();
	}
	
	@GetMapping("/{petId}/edit")
	public String initUpdateForm(@PathVariable Long petId, Model model) {
		model.addAttribute(PET_ATTR_NAME, this.petService.findById(petId));
		return VIEWS_CREATE_OR_UPDATE_PET_FORM;
	}
	
	@PostMapping("/{petId}/edit")
	public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
		if (result.hasErrors()) {
			pet.setOwner(owner);
			model.addAttribute(PET_ATTR_NAME, pet);
			return VIEWS_CREATE_OR_UPDATE_PET_FORM;
		}
		owner.addPet(pet);
		this.petService.save(pet);
		return ControllerGlobals.REDIRECT + "/owners/" + owner.getId();
	}
}
