package lt.codeacademy.kursutinklalapis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lt.codeacademy.kursutinklalapis.entities.MyUser;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;
import lt.codeacademy.kursutinklalapis.utils.Roles;

import org.slf4j.LoggerFactory;

import java.util.List;

import org.slf4j.Logger;

@Component
public class MyRunner implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(MyRunner.class);

	@Autowired
	UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		List<MyUser> users = repository.findAll();

		if (users.isEmpty()) {
			MyUser user = new MyUser();
			user.setUsername("admin");
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode("admin"));
			user.setRole(Roles.ADMIN);
			user = repository.save(user);
			logger.info("New user created: " + user);
		}
	}
}
