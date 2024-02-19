package es.teod.familiasec.configuracion;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
	@Bean

	public UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
	users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
	users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
	 "inner join usuarios u on u.username = up.username " +
			"inner join perfiles p on p.id_perfil = up.id_perfil " +
			"where u.username = ?");

	return users;

	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		// Los recursos estáticos no requieren autenticación
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("static/**").permitAll()
			// Las vistas públicas no requieren autenticación
			.requestMatchers("/signup","/", "/login", "/logout", "/search", "/app/producto/verUno/**").permitAll()
			.requestMatchers("/rest/encriptar/**").permitAll()
			// Todas las demás URLs de la Aplicación requieren autenticación
			// Asignar permisos a URLs por ROLES
	 		.requestMatchers("/app/producto/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMINISTRADOR")
			.requestMatchers("/app/usuarios/**").hasAnyAuthority("ROLE_GESTOR","ROLE_ADMINISTRADOR")
			.requestMatchers("/app/perfiles/**").hasAnyAuthority("ROLE_ADMINISTRADOR")
			.requestMatchers("/app/tipos/**").hasAnyAuthority("ROLE_GESTOR") 
			.anyRequest().authenticated())
		// El formulario de Login no requiere autenticacion
		.formLogin(form -> form.permitAll());
		return http.build();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	public InMemoryUserDetailsManager usersdetais() throws Exception{
		List<UserDetails> users=List.of(
				User
				.withUsername("user1")
				//.password("$2a$12$YUq1fO2Vbz.ONbIo./xmBeGCYFr5m4OLNC8H9HFafn4fpcOnUbqda")
				.password("{noop}user1")
				.roles("USERS")
				.build(),
				User
				.withUsername("user2")
				.password("{noop}user2")
				.roles("OPERATOR")
				.build(),
				User
				.withUsername("admin")
				.password("{noop}admin")
				.roles("USERS","ADMIN")
				.build());		
		return new InMemoryUserDetailsManager(users);					
	}
*/
	
	

}
