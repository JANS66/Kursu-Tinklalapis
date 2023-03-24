package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRep;

	public List<Student> getAllStudents() {
		return studentRep.findAll();
	}

	public Student getStudentById(Long id) {
		return studentRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
	}

	public Student createStudent(Student student) {
		return studentRep.save(student);
	}

	public Student updateStudent(Student student) {
		return studentRep.save(student);
	}

	public void deleteStudentById(Long id) {
		studentRep.deleteById(id);
	}

}
