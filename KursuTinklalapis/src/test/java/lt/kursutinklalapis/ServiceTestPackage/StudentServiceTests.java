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
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.services.StudentService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
public class StudentServiceTests {

	@Autowired
	private StudentService studServ;

	@Test
	@Order(1)
	void addAndCheckStudentTest() {
		Student st = new Student();
		st.setId(1L);
		st.setFirstName("Jonas");
		st.setLastName("Jonaitis");
		st.setEmail("jonukas@mail.com");

		studServ.createStudent(st);

		assertEquals("Jonas", studServ.getStudentById(1L).getFirstName());
		assertEquals("Jonaitis", studServ.getStudentById(1L).getLastName());
		assertEquals("jonukas@mail.com", studServ.getStudentById(1L).getEmail());
	}

	@Test
	@Order(2)
	void checkStudentsListTest() {
		assertNotNull(studServ.getAllStudents());
		assertNotNull(studServ.getStudentById(1L));
	}

	@Test
	@Order(3)
	void updateStudentInfoTest() {
		Student st = studServ.getStudentById(1L);
		st.setFirstName("Petras");
		st.setLastName("Petraitis");
		st.setEmail("petriukas@mail.com");

		studServ.updateStudent(1L, st);

		assertEquals("Petras", studServ.getStudentById(1L).getFirstName());
		assertEquals("Petraitis", studServ.getStudentById(1L).getLastName());
		assertEquals("petriukas@mail.com", studServ.getStudentById(1L).getEmail());
	}

	@Test
	@Order(4)
	void deleteStudent() {
		studServ.deleteStudentById(1L);
		assertTrue(studServ.getAllStudents().isEmpty());
	}
}
