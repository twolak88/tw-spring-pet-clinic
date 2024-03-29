package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

/**
 * @author twolak
 *
 */
@Service
@Profile({"default", "mapbased"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

	@Override
	public PetType findById(Long id) {
		return super.findById(id);
	}

	@Override
	public PetType save(PetType petType) {
		return super.save(petType);
	}

	@Override
	public Set<PetType> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(PetType petType) {
		super.delete(petType);
	}

}
