package lt.codeacademy.kursutinklalapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.kursutinklalapis.entities.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
