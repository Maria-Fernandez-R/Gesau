package es.gob.gesau.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.gob.gesau.model.UserEntity;

public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final UserEntity user;	

	public CustomUserDetails(UserEntity user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(
				r -> new SimpleGrantedAuthority(r.getName())).toList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	//el resto de metodos retorna true por defecto, se podria utiliza a futuro algun otro
	
	
}
