package es.gob.gesau.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class GesauSecurityConfig {

	private final UserDetailsService userDetailsService;

	public GesauSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() { UserDetails
	 * usuario1 =
	 * User.withUsername("usuario1").password(passwordEncoder().encode("123")).roles
	 * ("USER") .build(); UserDetails usuario2 =
	 * User.withUsername("usuario2").password(passwordEncoder().encode("456")).roles
	 * ("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(usuario1, usuario2); }
	 */

	// Forma recomendada
	/*
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {

		UserDetails usuario1 = User.withUsername("usuario1").password(encoder.encode("123")).roles("USER").build();

		UserDetails usuario2 = User.withUsername("usuario2").password(encoder.encode("456")).roles("USER").build();

		return new InMemoryUserDetailsManager(usuario1, usuario2);
	}
	*/

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/register", "/css/**", "/js/**")
				.permitAll().anyRequest().authenticated())
				.userDetailsService(userDetailsService)
				.formLogin(form -> form.loginPage("/login")
				.defaultSuccessUrl("/index.html", true)
				.failureUrl("/login.html?error=true").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login?logout"));

		return http.build();
	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf(Customizer.withDefaults()).authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//				.formLogin(form -> form.loginPage("/login").usernameParameter("email").permitAll())
//				.logout(logout -> logout.logoutSuccessUrl("/login?logout"))
//				.sessionManagement(session -> session.maximumSessions(1));
//		return http.build();
//	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth.requestMatchers("/login.html", "/css/**", "/js/**", "/images/**")
//						.permitAll().anyRequest().authenticated())
//				.formLogin(form -> form.loginPage("/login.html").loginProcessingUrl("/login.html")
//						.defaultSuccessUrl("/index.html", true).failureUrl("/login.html?error=true").permitAll())
//
//				.logout(logout -> logout.logoutUrl("/logout.html").logoutSuccessUrl("/login.html?logout=true"));
//
//		return http.build();
//	}

	

}
