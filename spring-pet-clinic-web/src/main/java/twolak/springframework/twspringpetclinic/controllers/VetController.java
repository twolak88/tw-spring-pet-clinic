/**
 * 
 */
package twolak.springframework.twspringpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@RequestMapping("/vets")
@Controller
public class VetController {
    
    private final VetService vetService;
    
    public VetController(VetService vetService) {
	this.vetService = vetService;
    }

    @GetMapping({"", "/index", "/index.html"})
    public String listVets(Model model) {
	
	model.addAttribute("vets", vetService.findAll());
	
	return "vets/index";
    }
}
