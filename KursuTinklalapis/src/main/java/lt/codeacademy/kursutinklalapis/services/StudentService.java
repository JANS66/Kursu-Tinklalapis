package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
		String hashedPassword = hashPassword(student.getPassword());
		student.setPassword(hashedPassword);
		return studentRep.save(student);
	}

	private String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
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
