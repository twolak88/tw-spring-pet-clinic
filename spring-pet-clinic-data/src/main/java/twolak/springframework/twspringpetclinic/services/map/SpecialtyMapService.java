package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Specialty;
import twolak.springframework.twspringpetclinic.services.SpecialtyService;

/**
 * @author twolak
 *
 */
@Service
@Profile({"default", "mapbased"})
public class SpecialtyMapService extends AbstractMapService<Specialty, Long> implements SpecialtyService {

	@Override
	public Specialty findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Specialty save(Specialty specialty) {
		return super.save(specialty);
	}

	@Override
	public Set<Specialty> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Specialty specialty) {
		super.delete(specialty);
	}

}
