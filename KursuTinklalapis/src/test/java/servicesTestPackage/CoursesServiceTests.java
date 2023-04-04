package servicesTestPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.KursuTinklalapisApplication;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.services.CourseService;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = KursuTinklalapisApplication.class )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoursesServiceTests {

	@Autowired
	private CourseService cServ;
	
	@Autowired
	private CourseRepository cRep;

	@Test
	@Order(1)
	void addCourseTest() {

		Professor prof1 = new Professor("Pirmas Destytojas", "pirmas@mail.com");
		Professor prof2 = new Professor("Antras Destytojas", "antras@mail.com");
		Professor prof3 = new Professor("Trecias Destytojas", "trecias@mail.com");
		
		Course cr1 = new Course("Fizika", "Mokslas apie fizikinius desnius", prof1.getFullName());
		Course cr2 = new Course("Matematika", "Algebra", prof2.getFullName());
		Course cr3 = new Course("Anglu", "Hello World", prof3.getFullName());

		cServ.saveCourse(cr1);
		cServ.saveCourse(cr2);
		cServ.saveCourse(cr3);
	}

	@Test
	@Order(2)
	void checkCoursesListTest() {
		assertNotNull(cServ.getAllCourses());
		assertNotNull(cServ.getCourseById(1L));
		assertNotNull(cServ.getCourseById(2L));
		assertNotNull(cServ.getCourseById(3L));
		
		assertEquals("Fizika", cServ.getCourseById(1L).getSubject());
		assertEquals("Algebra", cServ.getCourseById(2L).getDescription());
		assertEquals("Trecias Destytojas", cServ.getCourseById(3L).getProfessorName());
	}

	@Test
	@Order(3)
	@Transactional
	void updateCourseTest() {
		Course cr1 = cServ.getCourseById(1L);
		cr1.setProfessorName("Fizikos destytojas");
		cServ.updateCourse(1L, cr1);
		
		Course cr2 = cServ.getCourseById(2L);
		cr2.setDescription("Matematikos pagrindai");
		cServ.updateCourse(2L, cr2);
		
		Course cr3 = cServ.getCourseById(3L);
		cr3.setSubject("Amerikieciu kalba");
		cServ.updateCourse(3L, cr3);
		
		assertEquals("Fizikos destytojas", cServ.getCourseById(1L).getProfessorName());
		assertEquals("Matematikos pagrindai", cServ.getCourseById(2L).getDescription());
		assertEquals("Amerikieciu kalba", cServ.getCourseById(3L).getSubject());

	}

	@Test
	@Order(4)
	void deleteCourseTest() {
		cServ.deleteCourseById(3L);
		assertFalse(cRep.existsById(3L));
		
		cServ.deleteCourseById(1L);
		cServ.deleteCourseById(2L);
		
		assertFalse(cRep.existsById(1L));
		assertFalse(cRep.existsById(2L));
	}
}
