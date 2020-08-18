package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Pet;

/**
 * @author twolak
 *
 */
public interface PetRepository extends CrudRepository<Pet, Long> {

}
