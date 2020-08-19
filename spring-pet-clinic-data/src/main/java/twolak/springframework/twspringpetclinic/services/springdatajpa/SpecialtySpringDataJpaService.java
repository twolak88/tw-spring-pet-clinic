/**
 * 
 */
package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Specialty;
import twolak.springframework.twspringpetclinic.repositories.SpecialtyRepository;
import twolak.springframework.twspringpetclinic.services.SpecialtyService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class SpecialtySpringDataJpaService implements SpecialtyService {
	
	private final SpecialtyRepository specialtyRepository;
	
	public SpecialtySpringDataJpaService(SpecialtyRepository specialtyRepository) {
		this.specialtyRepository = specialtyRepository;
	}

	@Override
	public Specialty findById(Long id) {
		return this.specialtyRepository.findById(id).orElse(null);
	}

	@Override
	public Specialty save(Specialty specialty) {
		return this.specialtyRepository.save(specialty);
	}

	@Override
	public Set<Specialty> findAll() {
		Set<Specialty> specialties = new HashSet<>();
		this.specialtyRepository.findAll().forEach(specialties::add);
		return specialties;
	}

	@Override
	public void deleteById(Long id) {
		this.specialtyRepository.deleteById(id);
	}

	@Override
	public void delete(Specialty specialty) {
		this.specialtyRepository.delete(specialty);
	}

}
