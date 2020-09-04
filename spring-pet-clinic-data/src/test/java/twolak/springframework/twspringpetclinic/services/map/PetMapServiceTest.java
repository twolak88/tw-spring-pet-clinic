/**
 * 
 */
package twolak.springframework.twspringpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.services.PetService;

/**
 * @author twolak
 *
 */
class PetMapServiceTest {
	
	private final Long PET_ID = 1L;
	
	private PetService petService;
	
	@BeforeEach
	void setUp() throws Exception {
		this.petService = new PetMapService();
		this.petService.save(Pet.builder().id(PET_ID).build());
	}

	@Test
	void testFindAll() {
		Set<Pet> pets = this.petService.findAll();
		
		assertEquals(1, pets.size());
	}
	
	@Test
	public void testFindById() throws Exception {
		Pet pet = this.petService.findById(PET_ID);
		
		assertEquals(PET_ID, pet.getId());
	}
	
	@Test
	public void testFindByNotExistingId() throws Exception {
		Pet pet = this.petService.findById(5L);
		
		assertNull(pet);
	}
	
	@Test
	public void testFindByNullId() throws Exception {
		Pet pet = this.petService.findById(null);
		
		assertNull(pet);
	}
	
	@Test
	public void testSaveExistingId() throws Exception {
		Long id = 2L;
		Pet savedPet = this.petService.save(Pet.builder().id(id).build());
		assertEquals(id, savedPet.getId());
	}
	
	@Test
	public void testSaveDuplicateId() throws Exception {
		Long id = 1L;
		Pet savedPet = this.petService.save(Pet.builder().id(id).build());
		
		assertEquals(id, savedPet.getId());
		assertEquals(1, this.petService.findAll().size());
	}
	
	@Test
	public void testSaveNoId() throws Exception {
		Pet savedPet = this.petService.save(Pet.builder().build());
		
		assertNotNull(savedPet);
		assertNotNull(savedPet.getId());
		assertEquals(2, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteById() throws Exception {
		this.petService.deleteById(PET_ID);
		
		assertEquals(0, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteByIdWrongId() throws Exception {
		this.petService.deleteById(5L);
		
		assertEquals(1, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteByIdNullId() throws Exception {
		this.petService.deleteById(null);
		
		assertEquals(1, this.petService.findAll().size());
	}
	
	@Test
	public void testDelete() throws Exception {
		this.petService.delete(this.petService.findById(PET_ID));
		
		assertEquals(0, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteWithWrongId() throws Exception {
		this.petService.delete(Pet.builder().id(5L).build());
		
		assertEquals(1, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteNullId() throws Exception {
		this.petService.delete(Pet.builder().build());
		
		assertEquals(1, this.petService.findAll().size());
	}
	
	@Test
	public void testDeleteNull() throws Exception {
		this.petService.delete(null);
		
		assertEquals(1, this.petService.findAll().size());
	}
	

}
