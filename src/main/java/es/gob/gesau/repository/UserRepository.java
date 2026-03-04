package es.gob.gesau.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.gob.gesau.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
	
	Optional<UserEntity> findByUsername(String username);
	
}
