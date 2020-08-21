package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.services.OwnerService;

/**
 * @author twolak
 *
 */
public class OwnerMapServiceTest {
	
	private final Long OWNER_ID = 1L;
	private final String LAST_NAME = "Wolak";
	
	private OwnerService ownerService;

	@BeforeEach
	public void setUp() throws Exception {
		this.ownerService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		this.ownerService.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
	}
	
	@Test
	public void testFindAll() throws Exception {
		Set<Owner> owners = this.ownerService.findAll();
		
		assertEquals(1, owners.size());
	}
	
	@Test
	public void testFindById() throws Exception {
		Owner owner = this.ownerService.findById(OWNER_ID);
		
		assertEquals(OWNER_ID, owner.getId());
		
	}
	
	@Test
	public void testSaveExistingId() throws Exception {
		Long id = 2L;
		Owner owner = Owner.builder().id(id).build();
		Owner ownerSaved = this.ownerService.save(owner);
		
		assertEquals(id, ownerSaved.getId());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Owner ownerSaved = this.ownerService.save(Owner.builder().build());
		
		assertNotNull(ownerSaved);
		assertNotNull(ownerSaved.getId());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		this.ownerService.deleteById(OWNER_ID);
		
		assertEquals(0, this.ownerService.findAll().size());
	}
	
	@Test
	public void testDelete() throws Exception {
		this.ownerService.delete(this.ownerService.findById(OWNER_ID));
		
		assertEquals(0, this.ownerService.findAll().size());
		
	}
	
	@Test
	public void testFindByLastName() throws Exception {
		Owner owner = this.ownerService.findByLastName(LAST_NAME);
		
		assertNotNull(owner);
		assertEquals(LAST_NAME, owner.getLastName());
		assertEquals(OWNER_ID, owner.getId());
	}
	
	@Test
	public void testFindByLastNameNotFound() throws Exception {
		Owner owner = this.ownerService.findByLastName("last_name");
		
		assertNull(owner);
	}
}