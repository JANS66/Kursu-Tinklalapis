package lt.codeacademy.kursutinklalapis.services;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lt.codeacademy.kursutinklalapis.entities.MyUser;
import lt.codeacademy.kursutinklalapis.exceptions.UserAlreadyExistException;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser user = userRepository.findUserById(username);
		
		if( user == null)
			throw new UsernameNotFoundException(username);
		
		UserDetails user1 = User
				.withUsername( user.getUsername() )
				.password( user.getPassword()  )
				.roles( user.getRole())
				.build();
		
		return user1;
	}
	
	public List<MyUser> loadAllUsers(){
		return userRepository.findAll();
	}

	public MyUser registerNewUserAccount(MyUser userDto) throws UserAlreadyExistException {
		userDto.setPassword( encoder.encode(userDto.getPassword())  );
		
		MyUser user = userRepository.findUserById(userDto.getUsername());


		if(user != null) {
			logger.error("User exists, throwing exception");
			throw new UserAlreadyExistException();
		}
		logger.info("Saving to DB: " + userDto);
		return userRepository.save(userDto);
	}
}
