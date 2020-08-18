package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Visit;

/**
 * @author twolak
 *
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {

}
