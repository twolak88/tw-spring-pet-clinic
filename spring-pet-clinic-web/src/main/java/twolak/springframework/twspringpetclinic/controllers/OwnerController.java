package twolak.springframework.twspringpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping({ "", "index", "index.html" })
    public String listOwners(Model model) {

	model.addAttribute("owners", ownerService.findAll());

	return "owners/index";
    }

}
