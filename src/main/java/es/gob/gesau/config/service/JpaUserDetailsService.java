package es.gob.gesau.config.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.gob.gesau.model.UserEntity;
import es.gob.gesau.repository.UserRepository;
import es.gob.gesau.security.CustomUserDetails;

@Service
public class JpaUserDetailsService implements UserDetailsService{


	private final UserRepository userRepository;	
	
	public JpaUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username)
				.orElseThrow(()->
						new UsernameNotFoundException("Usuario no encontrado: "+username));
		return new CustomUserDetails(user);
	}

	
	
}
