package lt.codeacademy.kursutinklalapis.security;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class SecurityConfiguration {
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	/**
	 * This method is a Spring Security configuration method that sets up a security filter chain for handling incoming HTTP requests. The filter chain specifies which end-points and HTTP methods are allowed without authentication and which ones require authenticated users. The securityFilterChain method uses the HttpSecurity object to configure security rules for each endpoint and HTTP method. It sets up a list of allowed requests that can be accessed without authentication by using the requestMatchers method, and then sets the session creation policy to stateless. The method also configures an AuthenticationProvider bean that is responsible for authenticating users and retrieving their details from a user details service. It adds a JWT authentication filter before the UsernamePasswordAuthenticationFilter, which is responsible for handling authentication requests. Finally, the method configures logout handling by specifying a logout URL, adding a logout handler, and clearing the security context upon successful logout.
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable().and().csrf().disable().authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
				.requestMatchers(HttpMethod.GET, SecurityConstants.GET_COURSES, SecurityConstants.GET_PROFESSORS).permitAll()
				.requestMatchers(HttpMethod.GET, SecurityConstants.GET_STUDENTS).permitAll()
				.requestMatchers(HttpMethod.GET, SecurityConstants.GET_REGISTRATIONS).permitAll()
				.requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_STUDENTS).permitAll()
				.requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_COURSES, SecurityConstants.UPDATE_PROFESSORS).permitAll()
				.requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_REGISTRATIONS).permitAll()
				.requestMatchers(HttpMethod.POST, SecurityConstants.ADD_COURSES, SecurityConstants.ADD_PROFESSORS,SecurityConstants.ADD_STUDENTS).permitAll()						
				.requestMatchers(HttpMethod.POST, SecurityConstants.ADD_REGISTRATIONS).permitAll()
				.requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_STUDENTS, SecurityConstants.DELETE_PROFESSORS, SecurityConstants.DELETE_COURSES).permitAll()				
				.requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_REGISTRATIONS).permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).logout()
				.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

		return http.build();
	}
}
