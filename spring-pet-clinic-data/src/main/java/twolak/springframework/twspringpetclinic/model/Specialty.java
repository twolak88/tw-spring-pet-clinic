/**
 * 
 */
package twolak.springframework.twspringpetclinic.model;

/**
 * @author twolak
 *
 */
public class Specialty extends BaseEntity{
    
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
