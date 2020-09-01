package twolak.springframework.twspringpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
//@ToString(includeFieldNames=false)
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Builder
	public PetType(Long id, String name) {
		super(id);
		this.name = name;
	}
}
