package lt.codeacademy.kursutinklalapis.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	List<User> findByRole(Role student);

	Optional<User> findById(Long id);

	Optional<User> findByIdAndRole(Long id, Role role);
}
