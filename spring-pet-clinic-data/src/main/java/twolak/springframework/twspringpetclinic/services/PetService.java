/**
 * 
 */
package twolak.springframework.twspringpetclinic.services;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Pet;

/**
 * @author twolak
 *
 */
public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
