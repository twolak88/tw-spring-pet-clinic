package twolak.springframework.twspringpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.VetService;

/**
 * @author twolak
 *
 */
@Component
public class DataLoader implements CommandLineRunner{
    
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
	this.ownerService = ownerService;
	this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
	Owner owner1 = new Owner();
	owner1.setFirstName("Thomas");
	owner1.setLastName("Smith");
	
	ownerService.save(owner1);
	
	Owner owner2 = new Owner();
	owner2.setFirstName("Michael");
	owner2.setLastName("Weston");
	
	ownerService.save(owner2);
	
	Owner owner3 = new Owner();
	owner3.setFirstName("Fiona");
	owner3.setLastName("Glenanne");
	
	ownerService.save(owner3);
	
	System.out.println("Owners loaded...");
	
	Vet vet1 = new Vet();
	vet1.setFirstName("Patrick");
	vet1.setLastName("Jane");
	
	vetService.save(vet1);
	
	Vet vet2 = new Vet();
	vet2.setFirstName("Sam");
	vet2.setLastName("Axe");
	
	vetService.save(vet2);
	
	Vet vet3 = new Vet();
	vet3.setFirstName("Kurt");
	vet3.setLastName("Wild");
	
	vetService.save(vet3);
	
	System.out.println("Vets loaded...");
    }
}
