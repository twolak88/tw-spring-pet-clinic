package twolak.springframework.twspringpetclinic.controllers;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
		modelAndView.addObject(this.ownerService.findById(ownerId));
		return modelAndView;
	}
	

	@GetMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}
	
	@GetMapping("")
	public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
		
		if(owner.getLastName() == null) {
			owner.setLastName("");
		}
		
		Set<Owner> owners = this.ownerService.findAllByLastNameLike(owner.getLastName());
		
		if (owners.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (owners.size() == 1) {
			return "redirect:/owners/" + owners.iterator().next().getId();
		}
		else {
			model.addAttribute("owners", owners);
			return "owners/ownersList";
		}
	}
}
