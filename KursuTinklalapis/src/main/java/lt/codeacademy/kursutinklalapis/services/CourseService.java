package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;

@Service
@Transactional
public class CourseService {
	@Autowired
	private CourseRepository courseRep;

	public List<Course> getAllCourses() {
		return courseRep.findAll();
	}

	public Course getCourseById(Long id) {
		return courseRep.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
	}

	public Course saveCourse(Course course) {
		return courseRep.save(course);
	}

	public Course updateCourse(Long id, Course courseDetails) {
		Course course = courseRep.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course not found"));
		course.setSubject(courseDetails.getSubject());
		course.setDescription(courseDetails.getDescription());
		course.setProfessorName(courseDetails.getProfessorName());
		
		return courseRep.save(course);
	}

	public void deleteCourseById(Long id) {
		courseRep.deleteById(id);
	}
}
