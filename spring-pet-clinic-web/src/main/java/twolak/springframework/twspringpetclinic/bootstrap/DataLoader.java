package twolak.springframework.twspringpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;
import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
	this.ownerService = ownerService;
	this.vetService = vetService;
	this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
	createPets();
	createOwners();
	createVets();
    }

    private void createPets() {
	PetType dogPetType = createPetType("Dog");
	PetType catPetType = createPetType("Cat");
	PetType birdPetType = createPetType("Bird");
	
	System.out.println("PetTypes loaded...");
    }

    private PetType createPetType(String name) {
	PetType petType = new PetType();
	petType.setName(name);

	return petTypeService.save(petType);
    }

    private void createVets() {
	createVet("Patrick", "Jane");
	createVet("Sam", "Axe");
	createVet("Kurt", "Wild");

	System.out.println("Vets loaded...");
    }

    private void createVet(String firstName, String lastName) {
	Vet vet1 = new Vet();
	vet1.setFirstName(firstName);
	vet1.setLastName(lastName);

	vetService.save(vet1);
    }

    private void createOwners() {
	createOwner("Thomas", "Smith");
	createOwner("Michael", "Weston");
	createOwner("Fiona", "Glenanne");

	System.out.println("Owners loaded...");
    }

    private void createOwner(String firstName, String lastName) {
	Owner owner1 = new Owner();
	owner1.setFirstName(firstName);
	owner1.setLastName(lastName);

	ownerService.save(owner1);
    }
}
