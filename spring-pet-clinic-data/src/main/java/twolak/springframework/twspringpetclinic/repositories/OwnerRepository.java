package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Owner;

/**
 * @author twolak
 *
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
