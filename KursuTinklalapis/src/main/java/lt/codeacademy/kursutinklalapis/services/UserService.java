package lt.codeacademy.kursutinklalapis.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lt.codeacademy.kursutinklalapis.entities.MyUser;
import lt.codeacademy.kursutinklalapis.exceptions.EntityNotFoundException;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;

@Service

public class UserService  {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    
    public MyUser getUser(Long id) {
        Optional<MyUser> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }
    
    public MyUser getUser(String username) {
    	Optional<MyUser> user = userRepository.findByUsername(username);
       return unwrapUser(user, 404L);
    }
  
    public MyUser saveUser(MyUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    static MyUser unwrapUser(Optional<MyUser> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, MyUser.class);
    }
    
}
