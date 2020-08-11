/**
 * 
 */
package twolak.springframework.twspringpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author twolak
 *
 */
@Controller
public class VetController {
    
    @GetMapping({"/vets", "/vets/index.html", "/vets/index.html"})
    public String listVets() {
	return "vets/index";
    }
}
