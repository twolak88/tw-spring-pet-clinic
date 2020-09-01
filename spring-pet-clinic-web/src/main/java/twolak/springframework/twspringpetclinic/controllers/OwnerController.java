package twolak.springframework.twspringpetclinic.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import twolak.springframework.twspringpetclinic.config.ControllerGlobals;
import twolak.springframework.twspringpetclinic.config.DataBinderConfig;
import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.services.OwnerService;

/**
 * @author twolak
 *
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {

	private static final String OWNERS_ATTR_NAME = "owners";
	private static final String OWNER_ATTR_NAME = "owner";
	private static final String VIEWS_OWNERS_LIST = "owners/ownersList";
	private static final String VIEWS_OWNER_DETAILS = "owners/ownerDetails";
	private static final String VIEWS_FIND_OWNERS = "owners/findOwners";
	private static final String VIEWS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
	
	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields(DataBinderConfig.DISALLOWED_FIELDS_FOR_WEB_DATA_BINDER);
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView modelAndView = new ModelAndView(VIEWS_OWNER_DETAILS);
		modelAndView.addObject(this.ownerService.findById(ownerId));
		return modelAndView;
	}
	

	@GetMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute(OWNER_ATTR_NAME, Owner.builder().build());
		return VIEWS_FIND_OWNERS;
	}
	
	@GetMapping("")
	public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
		if(owner.getLastName() == null) {
			owner.setLastName("");
		}
		
		Set<Owner> owners = this.ownerService.findAllByLastNameLike(owner.getLastName());
		
		if (owners.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return VIEWS_FIND_OWNERS;
		}
		else if (owners.size() == 1) {
			return ControllerGlobals.REDIRECT + "/owners/" + owners.iterator().next().getId();
		}
		else {
			model.addAttribute(OWNERS_ATTR_NAME, owners);
			return VIEWS_OWNERS_LIST;
		}
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute(OWNER_ATTR_NAME, Owner.builder().build());
		return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		Owner savedOwner = this.ownerService.save(owner);
		return ControllerGlobals.REDIRECT + "/owners/" + savedOwner.getId();
	}
	
	@GetMapping("/{ownerId}/edit")
	public String initUpdateForm(@PathVariable Long ownerId, Model model) {
		model.addAttribute(OWNER_ATTR_NAME, this.ownerService.findById(ownerId));
		return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
	}
	
	@PostMapping("/{ownerId}/edit")
	public String processUpdateForm(@Valid Owner owner, BindingResult bindingResult, @PathVariable Long ownerId) {
		if (bindingResult.hasErrors()) {
//			model.addAttribute(OWNER_ATTR_NAME, owner);
			return VIEWS_CREATE_OR_UPDATE_OWNER_FORM;
		}
		owner.setId(ownerId);
		Owner savedOwner = this.ownerService.save(owner);
		return ControllerGlobals.REDIRECT + "/owners/" + savedOwner.getId();
	}
}
