package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private RegistrationRepository regRep;
	@Autowired
	private StudentRepository studentRep;
	@Autowired
	private CourseRepository courseRep;


	public List<Registration> getAllRegistrations() {
		return regRep.findAll();
	}

	public Registration getRegistrationById(Long id) {
		return regRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));
	}

	public Registration saveRegistration(Registration registration) {
		Student student = studentRep.findById(registration.getStudent().getId())
				.orElseThrow(() -> new EntityNotFoundException("Student not found"));
		Course course = courseRep.findById(registration.getCourse().getId())
				.orElseThrow(() -> new EntityNotFoundException("Course not found"));

		registration.setStudent(student);
		registration.setCourse(course);
		student.addRegistration(registration);
		course.setRegistrations(registration);

		return regRep.save(registration);
	}
	
	public Registration updateRegistration(Long id, Registration regDetails) {
		Registration registration = regRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));
		registration.setStudent(regDetails.getStudent());
		registration.setCourse(regDetails.getCourse());
		
		return regRep.save(registration);
	}

	public void deleteRegistrationById(Long id) {
		Registration registration = regRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));

		registration.getStudent().removeRegistration(registration);
		registration.getCourse().removeRegistration(registration);

		regRep.deleteById(id);
	}

}
