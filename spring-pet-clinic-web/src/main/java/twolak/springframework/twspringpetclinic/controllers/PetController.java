package twolak.springframework.twspringpetclinic.controllers;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	
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
}
