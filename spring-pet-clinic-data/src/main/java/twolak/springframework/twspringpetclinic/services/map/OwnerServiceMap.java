package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Owner;
import twolak.springframework.twspringpetclinic.services.OwnerService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    
    @Override
    public Set<Owner> findAll() {
	return super.findAll();
    }
    
    @Override
    public Owner findById(Long id) {
	return super.findById(id);
    }
    
    @Override
    public Owner save(Owner object) {
	return super.save(object.getId(), object);
    }
    
    @Override
    public void deleteById(Long id) {
	super.deleteById(id);
    }
    
    @Override
    public void delete(Owner object) {
	super.delete(object);;
    }

    @Override
    public Owner findByLastName(String lastName) {
	return this.findAll().stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst().orElse(null);
    }
}
