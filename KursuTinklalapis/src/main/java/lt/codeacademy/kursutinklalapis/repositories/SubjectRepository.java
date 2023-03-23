package lt.codeacademy.kursutinklalapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.kursutinklalapis.entities.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
