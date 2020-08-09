package twolak.springframework.twspringpetclinic.services;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Vet;

/**
 * @author twolak
 *
 */
public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
