/**
 * 
 */
package twolak.springframework.twspringpetclinic.services;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Owner;

/**
 * @author twolak
 *
 */
public interface OwnerService {
    Owner findByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
