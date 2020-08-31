package twolak.springframework.twspringpetclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Owner;

/**
 * @author twolak
 *
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	Owner findByLastName(String lastName);
	
	Set<Owner> findAllByLastNameLike(String lastName);
}
