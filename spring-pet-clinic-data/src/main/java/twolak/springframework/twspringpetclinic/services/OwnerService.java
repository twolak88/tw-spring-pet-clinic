package twolak.springframework.twspringpetclinic.services;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Owner;

/**
 * @author twolak
 *
 */
public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    
    Set<Owner> findAllByLastNameLike(String lastName);
}
