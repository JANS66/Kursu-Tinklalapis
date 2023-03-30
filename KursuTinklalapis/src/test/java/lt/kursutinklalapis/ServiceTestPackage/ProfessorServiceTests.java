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
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.services.ProfessorService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
class ProfessorServiceTests {

	@Autowired
	private ProfessorService profServ;

	@Test
	@Order(1)
	void addAndCheckProfessorTest() {
		Professor prof = new Professor();
		prof.setId(1L);
		prof.setFullName("Stephen Hawking");
		prof.setEmail("fizika@mail.com");

		profServ.createProfessor(prof);

		assertEquals("Stephen Hawking", profServ.getProfessorById(1L).getFullName());
		assertEquals("fizika@mail.com", profServ.getProfessorById(1L).getEmail());
	}

	@Test
	@Order(2)
	void checkProfessorsListTest() {
		assertNotNull(profServ.getAllProfessors());
		assertNotNull(profServ.getProfessorById(1L));
	}

	@Test
	@Order(3)
	void updateProfessorTest() {
		Professor prof = profServ.getProfessorById(1L);
		prof.setFullName("Barak Obama");
		prof.setEmail("politology@mail.com");

		profServ.updateProfessor(1L, prof);

		assertEquals("Barak Obama", profServ.getProfessorById(1L).getFullName());
		assertEquals("politology@mail.com", profServ.getProfessorById(1L).getEmail());

	}

	@Test
	@Order(4)
	void deleteProfessorTest() {
		profServ.deleteProfessor(1L);
		assertTrue(profServ.getAllProfessors().isEmpty());
	}
}
