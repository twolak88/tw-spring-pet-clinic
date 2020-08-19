/**
 * 
 */
package twolak.springframework.twspringpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Visit;
import twolak.springframework.twspringpetclinic.repositories.VisitRepository;
import twolak.springframework.twspringpetclinic.services.VisitService;

/**
 * @author twolak
 *
 */
@Service
@Profile("springdatajpa")
public class VisitSpringDataJpaService implements VisitService {
	
	private final VisitRepository visitRepository;

	public VisitSpringDataJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Visit findById(Long id) {
		return this.visitRepository.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit visit) {
		return this.visitRepository.save(visit);
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();
		this.visitRepository.findAll().forEach(visits::add);
		return visits;
	}

	@Override
	public void deleteById(Long id) {
		this.visitRepository.deleteById(id);
	}

	@Override
	public void delete(Visit visit) {
		this.visitRepository.delete(visit);
	}

}
