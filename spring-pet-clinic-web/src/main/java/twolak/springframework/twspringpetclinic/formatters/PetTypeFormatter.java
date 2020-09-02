package twolak.springframework.twspringpetclinic.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

/**
 * @author twolak
 *
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

	private final PetTypeService petTypeService;

	public PetTypeFormatter(PetTypeService petTypeService) {
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String petType, Locale locale) throws ParseException {
		return this.petTypeService.findAll().stream().filter(pt -> petType.equals(pt.getName())).findFirst()
				.orElseThrow(() -> new RuntimeException("Unknown Pet Type for name: " + petType));
	}

}
