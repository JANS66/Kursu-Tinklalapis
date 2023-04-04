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
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;
import lt.codeacademy.kursutinklalapis.services.StudentService;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = KursuTinklalapisApplication.class )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTests {

	@Autowired
	private StudentService studServ;
	
	@Autowired
	private StudentRepository studRep;

	@Test
	@Order(1)
	void addStudentTest() {
		Student st1 = new Student("Algis", "Algimantauskas", "algis@mail.com");
		Student st2 = new Student("Petras", "Ptrauskas", "petris@mail.com");
		Student st3 = new Student("Jonas", "Jonauskas", "jonas@mail.com");
		
		studServ.createStudent(st1);
		studServ.createStudent(st2);
		studServ.createStudent(st3);
	}

	@Test
	@Order(2)
	void checkStudentsListTest() {
		assertNotNull(studServ.getStudentById(1L));
		assertNotNull(studServ.getStudentById(2L));
		assertNotNull(studServ.getStudentById(3L));
		
		assertEquals("Algimantauskas", 	studServ.getStudentById(1L).getLastName());
		assertEquals("petris@mail.com", studServ.getStudentById(2L).getEmail());
		assertEquals(3L,				studServ.getStudentById(3L).getId());
	}

	@Test
	@Order(3)
	@Transactional
	void updateStudentInfoTest() {
		Student st1 = studServ.getStudentById(1L);
		st1.setFirstName("Petras");
		studServ.updateStudent(1L, st1);
		
		Student st2 = studServ.getStudentById(2L);
		st2.setEmail("naujas@mail.com");
		studServ.updateStudent(2L, st2);
		
		Student st3 = studServ.getStudentById(3L);
		st3.setLastName("Juozapaitis");
		studServ.updateStudent(3L, st3);
		
		assertEquals("Petras", studServ.getStudentById(1L).getFirstName());
		assertEquals("naujas@mail.com", studServ.getStudentById(2L).getEmail());
		assertEquals("Juozapaitis", studServ.getStudentById(3L).getLastName());
		
	}

	@Test
	@Order(4)
	void deleteStudentTest() {
		studServ.deleteStudentById(1L);
		
		assertFalse(studRep.existsById(1L));
		assertFalse(studRep.findAll().isEmpty());
		
		studServ.deleteStudentById(2L);
		studServ.deleteStudentById(3L);
		
		assertTrue(studRep.findAll().isEmpty());
		
	}
}
