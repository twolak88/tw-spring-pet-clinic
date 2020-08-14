package twolak.springframework.twspringpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Vet;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.VetService;
import twolak.springframework.twspringpetclinic.services.map.OwnerServiceMap;
import twolak.springframework.twspringpetclinic.services.map.VetServiceMap;

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
	owner1.setId(1L);
	owner1.setFirstName("Thomas");
	owner1.setLastName("Smith");
	
	ownerService.save(owner1);
	
	Owner owner2 = new Owner();
	owner2.setId(2L);
	owner2.setFirstName("Fiona");
	owner2.setLastName("Morison");
	
	ownerService.save(owner2);
	
	System.out.println("Owners loaded...");
	
	Vet vet1 = new Vet();
	vet1.setId(1L);
	vet1.setFirstName("Patrick");
	vet1.setLastName("Jane");
	
	vetService.save(vet1);
	
	Vet vet2 = new Vet();
	vet2.setId(2L);
	vet2.setFirstName("Sam");
	vet2.setLastName("Axe");
	
	vetService.save(vet2);
	
	System.out.println("Vets loaded...");
    }
}
