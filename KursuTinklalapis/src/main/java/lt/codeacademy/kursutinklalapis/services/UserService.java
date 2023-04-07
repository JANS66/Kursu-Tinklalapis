package lt.codeacademy.kursutinklalapis.services;

import lt.codeacademy.kursutinklalapis.entities.User;

public interface UserService {
	User getUser(Long id);

	User getUser(String username);

	User saveUser(User user);

}