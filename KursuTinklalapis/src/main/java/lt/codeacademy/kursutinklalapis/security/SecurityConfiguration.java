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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    	.headers().frameOptions().disable()
    	.and()
        .csrf().disable()       
        .authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()         
        .requestMatchers(HttpMethod.GET, SecurityConstants.GET_COURSES, SecurityConstants.GET_PROFESSORS).permitAll()   
        .requestMatchers(HttpMethod.GET, SecurityConstants.GET_STUDENTS).hasAnyAuthority(Role.ADMIN.name(), Role.PROFESSOR.name()) 
        .requestMatchers(HttpMethod.GET, SecurityConstants.GET_REGISTRATIONS).hasAnyAuthority(Role.ADMIN.name(), Role.PROFESSOR.name(), Role.STUDENT.name())    
        .requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_STUDENTS ).hasAnyAuthority(Role.ADMIN.name(), Role.STUDENT.name())
        .requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_COURSES, SecurityConstants.UPDATE_PROFESSORS ).hasAuthority(Role.ADMIN.name())
        .requestMatchers(HttpMethod.PUT, SecurityConstants.UPDATE_REGISTRATIONS).hasAnyAuthority(Role.ADMIN.name(), Role.STUDENT.name())
        .requestMatchers(HttpMethod.POST, SecurityConstants.ADD_COURSES, SecurityConstants.ADD_PROFESSORS, SecurityConstants.ADD_STUDENTS).hasAuthority(Role.ADMIN.name()) 
        .requestMatchers(HttpMethod.POST, SecurityConstants.ADD_REGISTRATIONS).hasAnyAuthority(Role.ADMIN.name(), Role.STUDENT.name())
        .requestMatchers(HttpMethod.DELETE,SecurityConstants.DELETE_STUDENTS, SecurityConstants.DELETE_PROFESSORS, SecurityConstants.DELETE_COURSES).hasAuthority(Role.ADMIN.name())       
        .requestMatchers(HttpMethod.DELETE,SecurityConstants.DELETE_STUDENTS, SecurityConstants.DELETE_PROFESSORS, SecurityConstants.DELETE_COURSES).hasAuthority(Role.ADMIN.name())       
        .requestMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_REGISTRATIONS).hasAnyAuthority(Role.ADMIN.name(), Role.STUDENT.name())                                          
        .anyRequest().authenticated()         
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)          
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout().logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler)               
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

    return http.build();
  }
}
