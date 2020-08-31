package twolak.springframework.twspringpetclinic.services.springdatajpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
		verifyNoMoreInteractions(this.ownerRepository);
	}
	
	@Test
	void testFindByIdNotFound() {
		when(this.ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = this.ownerSpringDataJpaService.findById(OWNER_ID);
		
		assertNull(owner);
		verify(this.ownerRepository, times(1)).findById(anyLong());
		verifyNoMoreInteractions(this.ownerRepository);
	}
	
	@Test
	void testSave() {
		when(this.ownerRepository.save(any())).thenReturn(returnedOwner);
		Owner savedOwner = this.ownerSpringDataJpaService.save(returnedOwner);
		
		assertNotNull(savedOwner);
		verify(this.ownerRepository, times(1)).save(any());
		verifyNoMoreInteractions(this.ownerRepository);
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
		verifyNoMoreInteractions(this.ownerRepository);
	}
	
	@Test
	void testDeleteById() {
		this.ownerSpringDataJpaService.deleteById(OWNER_ID);
		
		verify(this.ownerRepository, times(1)).deleteById(anyLong());
		verifyNoMoreInteractions(this.ownerRepository);
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
		verifyNoMoreInteractions(this.ownerRepository);
	}
	
	@Test
	void testFindAllByLastNameLike() {
		Set<Owner> owners = Stream.of(Owner.builder().id(1L).firstName(LAST_NAME).build(),
									Owner.builder().id(2L).firstName(LAST_NAME+1).build()).collect(Collectors.toSet());
		when(this.ownerRepository.findAllByLastNameLike(anyString())).thenReturn(owners);
		
		Set<Owner> ownersReturned = this.ownerSpringDataJpaService.findAllByLastNameLike(LAST_NAME);
		
		assertEquals(2, ownersReturned.size());
		verify(this.ownerRepository, times(1)).findAllByLastNameLike(anyString());
		verifyNoMoreInteractions(this.ownerRepository);
	}

}
