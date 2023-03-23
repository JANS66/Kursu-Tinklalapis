package lt.codeacademy.kursutinklalapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.kursutinklalapis.entities.Tutor;
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
