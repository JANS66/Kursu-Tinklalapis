package lt.codeacademy.kursutinklalapis.security.manager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lt.codeacademy.kursutinklalapis.entities.MyUser;
import lt.codeacademy.kursutinklalapis.services.UserService;

@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
	}
	
	@Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }

	
    private UserService userServiceImpl;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MyUser user = userServiceImpl.getUser(authentication.getName());
        if(!passwordEncoder().matches(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("You provided incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
    }
    
}
