/**
 * 
 */
package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.repositories.PetRepository;
import twolak.springframework.twspringpetclinic.services.PetService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class PetSpringDataJpaService implements PetService {
	
	private final PetRepository petRepository;

	public PetSpringDataJpaService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Pet findById(Long id) {
		return this.petRepository.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet pet) {
		return this.petRepository.save(pet);
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		this.petRepository.findAll().forEach(pets::add);
		return pets;
	}

	@Override
	public void deleteById(Long id) {
		this.petRepository.deleteById(id);
	}

	@Override
	public void delete(Pet pet) {
		this.petRepository.delete(pet);
	}

}
