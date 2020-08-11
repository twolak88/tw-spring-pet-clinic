package twolak.springframework.twspringpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author twolak
 *
 */
@Controller
public class IndexController {
    
    @GetMapping({"", "index", "index.html"})
    public String index() {
	return "index";
    }

}
