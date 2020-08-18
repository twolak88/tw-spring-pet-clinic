package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.PetType;

/**
 * @author twolak
 *
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}
