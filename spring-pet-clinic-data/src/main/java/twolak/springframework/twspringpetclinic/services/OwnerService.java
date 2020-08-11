package twolak.springframework.twspringpetclinic.services;

import twolak.springframework.twspringpetclinic.model.Owner;

/**
 * @author twolak
 *
 */
public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
