package twolak.springframework.twspringpetclinic.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
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
	
	PetType dogPetType = createPetType("Dog");
	PetType catPetType = createPetType("Cat");
	PetType birdPetType = createPetType("Bird");

	System.out.println("PetTypes loaded...");
	
	Owner tom = createOwner("Thomas", "Smith", "23 Perl", "Chicago", "342234567");
	tom.getPets().add(createPet(LocalDate.now().plusDays(-12), "Coco", birdPetType, tom));
	tom.getPets().add(createPet(LocalDate.now().plusDays(-543), "Dragon", dogPetType, tom));
	ownerService.save(tom);
	
	Owner mike = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "321456789");
	mike.getPets().add(createPet(LocalDate.now().plusDays(-123), "Pluto", dogPetType, mike));
	mike.getPets().add(createPet(LocalDate.now().plusDays(-764), "Guffi", dogPetType, mike));
	ownerService.save(mike);
	
	Owner fiona = createOwner("Fiona", "Glenanne", "23 Brickerel", "Miami", "765567898");
	fiona.getPets().add(createPet(LocalDate.now().plusDays(-432), "Luna", catPetType, fiona));
	fiona.getPets().add(createPet(LocalDate.now().plusDays(-888), "Maya", dogPetType, fiona));
	fiona.getPets().add(createPet(LocalDate.now().plusDays(-100), "Arnold", birdPetType, fiona));
	ownerService.save(fiona);

	System.out.println("Owners with pets loaded...");
	
	createVet("Patrick", "Jane");
	createVet("Sam", "Axe");
	createVet("Kurt", "Wild");

	System.out.println("Vets loaded...");
    }

    private Pet createPet(LocalDate birthDate, String name, PetType petType, Owner owner) {
	Pet pet = new Pet();
	pet.setName(name);
	pet.setPetType(petType);
	pet.setBirthDate(birthDate);
	pet.setOwner(owner);
	return pet;
    }

    private PetType createPetType(String name) {
	PetType petType = new PetType();
	petType.setName(name);

	return petType;
    }

    private void createVet(String firstName, String lastName) {
	Vet vet = new Vet();
	vet.setFirstName(firstName);
	vet.setLastName(lastName);

	vetService.save(vet);
    }

    private Owner createOwner(String firstName, String lastName, String address, String city, String phone) {
	Owner owner = new Owner();
	owner.setFirstName(firstName);
	owner.setLastName(lastName);
	owner.setAddress(address);
	owner.setCity(city);
	owner.setPhone(phone);
	return owner;
    }
}
