package lt.kursutinklalapis.ServiceTestPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import lt.codeacademy.kursutinklalapis.KursuTinklalapisApplication;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.services.CourseService;
import lt.codeacademy.kursutinklalapis.services.ProfessorService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
public class CoursesServiceTests {

	@Autowired
	private CourseService cServ;

	@Autowired
	private ProfessorService profServ;

	@Test
	@Order(1)
	void addCourseAndCheckTest() {
		Professor prof = new Professor();
		prof.setId(1L);
		prof.setFullName("Stephen Hawking");
		prof.setEmail("fizika@mail.com");

		profServ.createProfessor(prof);

		Course cr = new Course();
		cr.setId(1L);
		cr.setSubject("Fizika");
		cr.setDescription("Mokslas apie fizikinius desnius");
		cr.setProfessorName(profServ.getProfessorById(1L).getFullName());

		cServ.saveCourse(cr);

		assertEquals("Fizika", cServ.getCourseById(1L).getSubject());
		assertEquals("Mokslas apie fizikinius desnius", cServ.getCourseById(1L).getDescription());
		assertEquals("Stephen Hawking", cServ.getCourseById(1L).getProfessorName());
	}

	@Test
	@Order(2)
	void checkCoursesListTest() {
		assertNotNull(cServ.getAllCourses());
		assertNotNull(cServ.getCourseById(1L));
	}

	@Test
	@Order(3)
	void updateCourseTest() {
		Course cr = cServ.getCourseById(1L);
		cr.setSubject("Quantum physics");
		cr.setDescription("Study of quantum physics");

		cServ.updateCourse(1L, cr);

		assertEquals("Quantum physics", cServ.getCourseById(1L).getSubject());
		assertEquals("Study of quantum physics", cServ.getCourseById(1L).getDescription());
		assertEquals("Stephen Hawking", cServ.getCourseById(1L).getProfessorName());

	}

	@Test
	@Order(4)
	void deleteCourseTest() {
		cServ.deleteCourseById(1L);
		assertTrue(cServ.getAllCourses().isEmpty());
	}
}
