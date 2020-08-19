package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Specialty;
import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.services.SpecialtyService;
import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@Service
@Profile({"default", "mapbased"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Vet save(Vet vet) {
		if (vet.getSpecialties().size() > 0) {
			vet.getSpecialties().forEach(specialty -> {
				if (specialty.getId() == null) {
					Specialty savedSpecialty = specialtyService.save(specialty);
					specialty.setId(savedSpecialty.getId());
				}
			});
		}
		return super.save(vet);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet vet) {
		super.delete(vet);
		;
	}
}
