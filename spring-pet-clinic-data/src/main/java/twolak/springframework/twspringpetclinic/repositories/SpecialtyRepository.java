package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Specialty;

/**
 * @author twolak
 *
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

}
