package twolak.springframework.twspringpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author twolak
 *
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {
    
	@Column(name = "address")
    private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
	
	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String phone, Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.phone = phone;
		if (pets == null || pets.size() > 0) {
			this.pets = pets;
		}
	}
	
	public void addPet(Pet pet) {
		pet.setOwner(this);
		this.pets.add(pet);
	}
	
	public Pet getPet(String name) {
		return getPet(name, false);
	}
	
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : pets) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}
}
