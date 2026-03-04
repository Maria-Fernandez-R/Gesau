package es.gob.gesau.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gob.gesau.model.RoleEntity;
import es.gob.gesau.model.UserEntity;

public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
	
	Optional<RoleEntity> findByName(String name);
	
}
