package es.gob.gesau.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import es.gob.gesau.model.Autorizacion;

public interface AutorizacionRepository extends CrudRepository<Autorizacion,String>{
	
	List<Autorizacion> findAll();

	
}
