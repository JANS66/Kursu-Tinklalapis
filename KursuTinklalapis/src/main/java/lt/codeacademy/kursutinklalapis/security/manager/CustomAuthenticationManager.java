package lt.codeacademy.kursutinklalapis.security.manager;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lt.codeacademy.kursutinklalapis.entities.User;
import lt.codeacademy.kursutinklalapis.services.UserService;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

	private UserService userServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		User user = userServiceImpl.getUser(authentication.getName());
		if (!passwordEncoder().matches(authentication.getCredentials().toString(), user.getPassword())) {
			throw new BadCredentialsException("You provided an incorrect password.");
		}

		return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
	}
}
