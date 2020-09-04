/**
 * 
 */
package twolak.springframework.twspringpetclinic.controllers;

import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@Controller
public class VetController {

	private final VetService vetService;

	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@GetMapping({ "/vets", "/vets/index", "/vets/index.html" })
	public String listVets(Model model) {

		model.addAttribute("vets", vetService.findAll());

		return "vets/index";
	}
	
	@GetMapping(path = "/api/vets", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Set<Vet> getVetsJson() {
		return vetService.findAll();
	}
	
	@GetMapping(path = "/api/xml/vets", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Set<Vet> getVetsXml() {
		return vetService.findAll();
	}
}
