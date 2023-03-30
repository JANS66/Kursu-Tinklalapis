package lt.codeacademy.kursutinklalapis.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

import lt.codeacademy.kursutinklalapis.security.manager.CustomAuthenticationManager;

import org.springframework.security.config.http.SessionCreationPolicy;
//import static lt.codeacademy.kursutinklalapis.utils.Roles.*;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	CustomAuthenticationManager customAuthenticationManager;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	    AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
	    authenticationFilter.setFilterProcessesUrl("/authenticate");

		
		http
        .headers().frameOptions().disable() 
        .and()
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/admin/**").hasRole("ADMIN") 
        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();

	}
	
}
