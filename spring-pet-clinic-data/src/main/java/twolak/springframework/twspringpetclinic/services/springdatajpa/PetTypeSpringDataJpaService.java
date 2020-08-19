/**
 * 
 */
package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.repositories.PetTypeRepository;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class PetTypeSpringDataJpaService implements PetTypeService {
	
	private final PetTypeRepository petTypeRepository;

	public PetTypeSpringDataJpaService(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public PetType findById(Long id) {
		return this.petTypeRepository.findById(id).orElse(null);
	}

	@Override
	public PetType save(PetType petType) {
		return this.petTypeRepository.save(petType);
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<>();
		this.petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public void deleteById(Long id) {
		this.petTypeRepository.deleteById(id);
	}

	@Override
	public void delete(PetType petType) {
		this.delete(petType);
	}

}
