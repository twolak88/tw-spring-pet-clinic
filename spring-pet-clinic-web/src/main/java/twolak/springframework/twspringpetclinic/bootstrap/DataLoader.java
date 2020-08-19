package twolak.springframework.twspringpetclinic.bootstrap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.model.Specialty;
import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.model.Visit;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;
import twolak.springframework.twspringpetclinic.services.SpecialtyService;
import twolak.springframework.twspringpetclinic.services.VetService;
import twolak.springframework.twspringpetclinic.services.VisitService;

/**
 * @author twolak
 *
 */
@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final SpecialtyService specialtyService;
	private final PetTypeService petTypeService;
	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, SpecialtyService specialtyService,
			PetTypeService petTypeService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.specialtyService = specialtyService;
		this.petTypeService = petTypeService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {

		if (this.petTypeService.findAll().isEmpty()) {
			loadData();
		}
	}

	private void loadData() {
		PetType dogPetType = createPetType("Dog");
		this.petTypeService.save(dogPetType);
		PetType catPetType = createPetType("Cat");
		this.petTypeService.save(catPetType);
		PetType birdPetType = createPetType("Bird");
		this.petTypeService.save(birdPetType);

		System.out.println("PetTypes loaded...");

		Owner tom = createOwner("Thomas", "Smith", "23 Perl", "Chicago", "342234567");
		tom.getPets().add(createPet(LocalDate.now().plusDays(-12), "Coco", birdPetType, tom));
		tom.getPets().add(createPet(LocalDate.now().plusDays(-543), "Dragon", dogPetType, tom));
		this.ownerService.save(tom);

		Owner mike = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "321456789");
		Pet dog = createPet(LocalDate.now().plusDays(-123), "Pluto", dogPetType, mike);
		mike.getPets().add(dog);
		mike.getPets().add(createPet(LocalDate.now().plusDays(-764), "Guffi", dogPetType, mike));
		this.ownerService.save(mike);

		Owner fiona = createOwner("Fiona", "Glenanne", "23 Brickerel", "Miami", "765567898");
		Pet cat = createPet(LocalDate.now().plusDays(-432), "Luna", catPetType, fiona);
		fiona.getPets().add(cat);
		fiona.getPets().add(createPet(LocalDate.now().plusDays(-888), "Maya", dogPetType, fiona));
		fiona.getPets().add(createPet(LocalDate.now().plusDays(-100), "Arnold", birdPetType, fiona));
		this.ownerService.save(fiona);

		System.out.println("Owners with pets loaded...");
		
		Visit catVisit = createVisit(LocalDate.now(), "Sneezy Kitty", cat);
		this.visitService.save(catVisit);
		
		Visit dogVisit = createVisit(LocalDate.now(), "Some dog with borken leg", dog);
		this.visitService.save(dogVisit);
		
		System.out.println("Visits loaded...");

		Specialty radiology = createSpecialty("radiology");
		this.specialtyService.save(radiology);
		Specialty surgery = createSpecialty("surgery");
		this.specialtyService.save(surgery);
		Specialty dentistry = createSpecialty("dentistry");
		this.specialtyService.save(dentistry);

		Set<Specialty> specialties = new HashSet<>();
		specialties.add(radiology);
		specialties.add(surgery);
		Vet patrick = createVet("Patrick", "Jane", specialties);
		this.vetService.save(patrick);

		specialties.clear();
		specialties.add(dentistry);
		Vet sam = createVet("Sam", "Axe", specialties);
		this.vetService.save(sam);

		specialties.clear();
		specialties.add(surgery);
		specialties.add(dentistry);
		Vet kurt = createVet("Kurt", "Wild", specialties);
		this.vetService.save(kurt);

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
	
	private Visit createVisit(LocalDate date, String description, Pet pet) {
		Visit visit = new Visit();
		visit.setDate(date);
		visit.setDescription(description);
		visit.setPet(pet);
		return visit;
	}

	private PetType createPetType(String name) {
		PetType petType = new PetType();
		petType.setName(name);
		return petType;
	}

	private Vet createVet(String firstName, String lastName, Set<Specialty> specialties) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		vet.getSpecialties().addAll(specialties);
		return vet;
	}

	private Specialty createSpecialty(String name) {
		Specialty specialty = new Specialty();
		specialty.setDescription(name);
		return specialty;
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
