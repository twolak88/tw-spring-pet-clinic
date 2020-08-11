package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import twolak.springframework.twspringpetclinic.model.Pet;
import twolak.springframework.twspringpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long>{

    @Override
    public Set<Pet> findAll() {
	return super.findAll();
    }
    
    @Override
    public Pet findById(Long id) {
	return super.findById(id);
    }
    
    @Override
    public Pet save(Pet object) {
	return super.save(object.getId(), object);
    }
    
    @Override
    public void deleteById(Long id) {
	super.deleteById(id);
    }
    
    @Override
    public void delete(Pet object) {
	super.delete(object);;
    }
}
