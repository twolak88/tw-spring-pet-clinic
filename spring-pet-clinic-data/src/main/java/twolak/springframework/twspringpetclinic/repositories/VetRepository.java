package twolak.springframework.twspringpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import twolak.springframework.twspringpetclinic.model.Vet;

/**
 * @author twolak
 *
 */
public interface VetRepository extends CrudRepository<Vet, Long> {

}
