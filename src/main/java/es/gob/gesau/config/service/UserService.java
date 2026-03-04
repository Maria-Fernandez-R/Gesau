package es.gob.gesau.config.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.gob.gesau.model.RoleEntity;
import es.gob.gesau.model.UserEntity;
import es.gob.gesau.repository.RoleRepository;
import es.gob.gesau.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}
	
	public UserEntity register(String username, String rawPassword) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(rawPassword));
		user.setEnabled(true);
		
		RoleEntity userRole = roleRepository.findByName("ROLE_USER").orElseThrow();
		user.getRoles().add(userRole);
		
		return userRepository.save(user);
	}
	

}
