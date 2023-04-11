package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Registration;
import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.entities.User;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.repositories.RegistrationRepository;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;

@Service
@Transactional
public class RegistrationService {

	@Autowired
	private RegistrationRepository regRep;

	@Autowired
	private CourseRepository courseRep;

	@Autowired
	UserRepository userRep;

	public List<Registration> getAllRegistrations() {
		return regRep.findAll();
	}

	public Registration getRegistrationById(Long id) {
		return regRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Registration not found"));
	}

	public Registration saveRegistration(Registration registration) {
		User student = userRep.findById(registration.getUser().getId())
				.orElseThrow(() -> new EntityNotFoundException("Student not found"));
		Course course = courseRep.findById(registration.getCourse().getId())
				.orElseThrow(() -> new EntityNotFoundException("Course not found"));

		registration.setUser(student);
		registration.setCourse(course);
		student.addRegistration(registration);
		course.addRegistration(registration);

		return regRep.save(registration);
	}

	public Registration updateRegistration(Long id, Registration regDetails) {
		Registration registration = regRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));
		registration.setUser(regDetails.getUser());
		registration.setCourse(regDetails.getCourse());

		return regRep.save(registration);
	}

	public void deleteRegistrationById(Long id) {
		Registration registration = regRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Registration not found"));

		registration.getUser().removeRegistration(registration);
		registration.getCourse().removeRegistration(registration);

		regRep.deleteById(id);
	}

}
