package es.gob.gesau.config.service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private final String ROLE_USER = "ROLE_USER";
	private final String ROLE_ADMIN = "ROLE_ADMIN";
	private final String ROLE_CONSULTOR = "ROLE_CONSULTOR";

	
	
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

	public UserEntity changePassword(String currentPassword, String newPassword, String confirmPassword) {

		UserEntity user = userRepository.findByUsername(getUserNameUsuarioLogeado()).orElse(null);

		if (user == null) {
			throw new RuntimeException("Usuario no encontrado");
		}

		if (currentPassword == null || currentPassword.isBlank()) {
			throw new RuntimeException("Debes introducir la contraseña actual");
		}

		if (newPassword == null || newPassword.isBlank()) {
			throw new RuntimeException("Debes introducir la nueva contraseña");
		}

		if (confirmPassword == null || confirmPassword.isBlank()) {
			throw new RuntimeException("Debes repetir la nueva contraseña");
		}

		if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
			throw new RuntimeException("La contraseña actual no es correcta");
		}

		if (!newPassword.equals(confirmPassword)) {
			throw new RuntimeException("Las nuevas contraseñas no coinciden");
		}

		if (newPassword.length() < 8) {
			throw new RuntimeException("La nueva contraseña debe tener al menos 8 caracteres");
		}

		if (passwordEncoder.matches(newPassword, user.getPassword())) {
			throw new RuntimeException("La nueva contraseña no puede ser igual a la actual");
		}

		user.setPassword(passwordEncoder.encode(newPassword));
		return userRepository.save(user);
	}

	public String getUserAndRoleUsuarioLogeado(){
		String userAndRole = "";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null){
			userAndRole += authentication.getName();

			String rol = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(ROLE_USER);

			//solo colocar el tipo de usuario cuando no son usuarios comunes: ROLE_USER
			if(ROLE_ADMIN.equals(rol)) userAndRole+=" (administrador)";
			if(ROLE_CONSULTOR.equals(rol)) userAndRole+=" (consultor)";
		}

		return userAndRole;
	}

	public String getUserNameUsuarioLogeado() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}	

}
