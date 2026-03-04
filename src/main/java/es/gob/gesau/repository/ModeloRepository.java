package es.gob.gesau.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.gob.gesau.model.Modelo;

public interface ModeloRepository extends CrudRepository<Modelo, String>{

	Optional<Modelo> findById(String idModelo);
}
