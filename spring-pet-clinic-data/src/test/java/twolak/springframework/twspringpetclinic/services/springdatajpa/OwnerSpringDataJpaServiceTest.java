package twolak.springframework.twspringpetclinic.services.springdatajpa;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.repositories.OwnerRepository;
import twolak.springframework.twspringpetclinic.repositories.PetRepository;
import twolak.springframework.twspringpetclinic.repositories.PetTypeRepository;

/**
 * @author twolak
 *
 */
@ExtendWith(MockitoExtension.class)
public class OwnerSpringDataJpaServiceTest {
	
	private static final long OWNER_ID = 1L;

	private static final String LAST_NAME = "Wolak";

	@InjectMocks
	private OwnerSpringDataJpaService ownerSpringDataJpaService;
	
	@Mock
	private OwnerRepository ownerRepository;
	@Mock
	private PetRepository petRepository;
	@Mock
	private PetTypeRepository petTypeRepository;
	
	Owner returnedOwner;
	
	@BeforeEach
	void setUp() throws Exception {
		returnedOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
	}
	
	@Test
	@Disabled
	void testOwnerSpringDataJpaService() {
		fail("Not yet implemented");
	}
	
	@Test
	void testFindById() {
		when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));
		
		Owner owner = this.ownerSpringDataJpaService.findById(OWNER_ID);
		
		assertNotNull(owner);
		verify(this.ownerRepository, times(1)).findById(anyLong());
	}
	
	@Test
	void testFindByIdNotFound() {
		when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = this.ownerSpringDataJpaService.findById(OWNER_ID);
		
		assertNull(owner);
		verify(this.ownerRepository, times(1)).findById(anyLong());
	}
	
	@Test
	void testSave() {
		when(this.ownerRepository.save(any())).thenReturn(returnedOwner);
		Owner savedOwner = this.ownerSpringDataJpaService.save(returnedOwner);
		
		assertNotNull(savedOwner);
		verify(this.ownerRepository, times(1)).save(any());
	}
	
	@Test
	void testFindAll() {
		Set<Owner> returnedOwners = new HashSet<>();
		returnedOwners.add(returnedOwner);
		returnedOwners.add(Owner.builder().id(OWNER_ID+1).build());
		int countOfOwners = returnedOwners.size();
		
		when(this.ownerRepository.findAll()).thenReturn(returnedOwners);
		
		Set<Owner> foundOwners = this.ownerSpringDataJpaService.findAll();
		
		assertNotNull(foundOwners);
		assertEquals(countOfOwners, foundOwners.size());
		verify(this.ownerRepository, times(1)).findAll();
	}
	
	@Test
	void testDeleteById() {
		this.ownerSpringDataJpaService.deleteById(OWNER_ID);
		
		verify(this.ownerRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void testDelete() {
		this.ownerSpringDataJpaService.delete(returnedOwner);
		
		verify(this.ownerRepository, times(1)).delete(any());
	}
	
	@Test
	void testFindByLastName() {
		when(this.ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
		
		Owner owner = this.ownerSpringDataJpaService.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, owner.getLastName());
		verify(this.ownerRepository, times(1)).findByLastName(any());
	}

}
