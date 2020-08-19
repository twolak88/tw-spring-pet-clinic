package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.repositories.VetRepository;
import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class VetSpringDataJpaService implements VetService {
	
	private final VetRepository vetRepository;

	public VetSpringDataJpaService(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Vet findById(Long id) {
		return this.vetRepository.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet vet) {
		return this.vetRepository.save(vet);
	}

	@Override
	public Set<Vet> findAll() {
		Set<Vet> vets = new HashSet<>();
		this.vetRepository.findAll().forEach(vets::add);
		return vets;
	}

	@Override
	public void deleteById(Long id) {
		this.vetRepository.deleteById(id);
	}

	@Override
	public void delete(Vet vet) {
		this.vetRepository.delete(vet);
	}

}
