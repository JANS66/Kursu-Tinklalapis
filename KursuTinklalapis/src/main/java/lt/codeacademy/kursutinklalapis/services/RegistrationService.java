package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Registration;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.repositories.RegistrationRepository;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;

@Service
@Transactional
public class RegistrationService {

	private final RegistrationRepository registrationRepository;
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	public RegistrationService(RegistrationRepository registrationRepository, StudentRepository studentRepository,
			CourseRepository courseRepository) {
		this.registrationRepository = registrationRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public List<Registration> getAllRegistrations() {
		return registrationRepository.findAll();
	}

	public Registration getRegistrationById(Long id) {
		return registrationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));
	}

	public Registration saveRegistration(Registration registration) {
		Student student = studentRepository.findById(registration.getStudent().getId())
				.orElseThrow(() -> new EntityNotFoundException("Student not found"));
		Course course = courseRepository.findById(registration.getCourse().getId())
				.orElseThrow(() -> new EntityNotFoundException("Course not found"));

		registration.setStudent(student);
		registration.setCourse(course);
		student.addRegistration(registration);
		course.setRegistrations(registration);

		return registrationRepository.save(registration);
	}

	public void deleteRegistrationById(Long id) {
		Registration registration = registrationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));

		registration.getStudent().removeRegistration(registration);
		registration.getCourse().removeRegistration(registration);

		registrationRepository.deleteById(id);
	}

}
