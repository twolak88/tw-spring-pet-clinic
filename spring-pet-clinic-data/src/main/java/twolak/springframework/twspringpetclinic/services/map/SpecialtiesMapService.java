package twolak.springframework.twspringpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import twolak.springframework.twspringpetclinic.model.Specialty;
import twolak.springframework.twspringpetclinic.services.SpecialtiesService;

/**
 * @author twolak
 *
 */
@Service
public class SpecialtiesMapService extends AbstractMapService<Specialty, Long> implements SpecialtiesService{

    @Override
    public Specialty findById(Long id) {
	return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object) {
	return super.save(object);
    }

    @Override
    public Set<Specialty> findAll() {
	return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
	super.deleteById(id);
    }

    @Override
    public void delete(Specialty object) {
	super.delete(object);
    }

}
