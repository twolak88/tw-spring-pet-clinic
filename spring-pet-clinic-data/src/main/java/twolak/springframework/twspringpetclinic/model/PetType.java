package twolak.springframework.twspringpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
//@ToString(includeFieldNames=false)
@Builder
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Override
	public String toString() {
		return this.name;
	}
}
