package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.repositories.OwnerRepository;
import twolak.springframework.twspringpetclinic.repositories.PetRepository;
import twolak.springframework.twspringpetclinic.repositories.PetTypeRepository;
import twolak.springframework.twspringpetclinic.services.OwnerService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class OwnerSpringDataJpaService implements OwnerService {
	
	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	
	public OwnerSpringDataJpaService(OwnerRepository ownerRepository, PetTypeRepository petTypeRepository, PetRepository petRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findById(Long id) {
		return this.ownerRepository.findById(id).orElse(null);
	}

	@Override
	public Owner save(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		this.ownerRepository.findAll().forEach(owners::add);
		return  owners;
	}

	@Override
	public void deleteById(Long id) {
		this.ownerRepository.deleteById(id);
	}

	@Override
	public void delete(Owner owner) {
		this.ownerRepository.delete(owner);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return this.ownerRepository.findByLastName(lastName);
	}

}
