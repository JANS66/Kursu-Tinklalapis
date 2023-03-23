package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;

@Service
@Transactional
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseById(Long id) {
		return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
	}

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}
}
