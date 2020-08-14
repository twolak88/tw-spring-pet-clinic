package twolak.springframework.twspringpetclinic.model;

import java.io.Serializable;

/**
 * @author twolak
 *
 */
public class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
