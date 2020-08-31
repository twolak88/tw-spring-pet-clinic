package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.model.PetType;
import twolak.springframework.twspringpetclinic.services.OwnerService;
import twolak.springframework.twspringpetclinic.services.PetService;
import twolak.springframework.twspringpetclinic.services.PetTypeService;

/**
 * @author twolak
 *
 */
@Service
@Profile({"default", "mapbased"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerMapService(PetTypeService petTypeService, PetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {
		return super.findById(id);
	}

	@Override
	public Owner save(Owner owner) {
		if (owner != null) {
			if (owner.getPets() != null) {
				owner.getPets().forEach(pet -> {
					if (pet.getPetType() != null) {
						if (pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}
					} else {
						throw new RuntimeException("Pet Type is required");
					}

					if (pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(owner);
		} else {
			return null;
		}

	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Owner owner) {
		super.delete(owner);
		;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return this.findAll().stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst()
				.orElse(null);
	}

	@Override
	public Set<Owner> findAllByLastNameLike(String lastName) {
		// TODO will be implemented in future
		return null;
	}
}
