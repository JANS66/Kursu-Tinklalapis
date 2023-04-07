package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;
import lt.codeacademy.kursutinklalapis.utils.ERole;

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
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		student.setPassword(encoder.encode(student.getPassword()));
		student.setRole(ERole.ROLE_USER);
		;

		return studentRep.save(student);
	}

	public Student updateStudent(Long id, Student student) {
		Student existingStudent = studentRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setRegistrations(student.getRegistrations());

		return studentRep.save(existingStudent);
	}

	public void deleteStudentById(Long id) {
		studentRep.deleteById(id);
	}

}
