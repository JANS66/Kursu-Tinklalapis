package lt.codeacademy.kursutinklalapis.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.services.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}

	@PostMapping
	public ResponseEntity createStudent(@RequestBody Student student) throws URISyntaxException {
		Student newStudent = studentService.createStudent(student);
		return ResponseEntity.created(new URI("/students/" + newStudent.getId())).body(newStudent);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateStudent(@PathVariable Long id, @RequestBody Student student) {
		Student currentStudent = studentService.updateStudent(id, student);
		return ResponseEntity.ok(currentStudent);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.ok().build();
	}
}
