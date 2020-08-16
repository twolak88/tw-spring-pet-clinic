package twolak.springframework.twspringpetclinic.model;

import java.util.Set;

/**
 * @author twolak
 *
 */
public class Vet extends Person {

    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
