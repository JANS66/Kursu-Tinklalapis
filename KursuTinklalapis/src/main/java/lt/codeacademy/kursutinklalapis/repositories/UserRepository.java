package lt.codeacademy.kursutinklalapis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.kursutinklalapis.entities.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {

	
	Optional<MyUser> findByUsername(String username);
	MyUser findUserById(String username);
}
