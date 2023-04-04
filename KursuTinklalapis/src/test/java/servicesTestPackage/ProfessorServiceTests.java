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
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.repositories.ProfessorRepository;
import lt.codeacademy.kursutinklalapis.services.ProfessorService;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest(classes = KursuTinklalapisApplication.class )
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProfessorServiceTests {

	@Autowired
	private ProfessorService profServ;
	
	@Autowired
	private ProfessorRepository profRep;

	@Test
	@Order(1)
	void addProfessorTest() {
		Professor prof1 = new Professor("Pirmas Destytojas", "pirmas@mail.com");
		Professor prof2 = new Professor("Antras Destytojas", "antras@mail.com");
		Professor prof3 = new Professor("Trecias Destytojas", "trecias@mail.com");
		

		profServ.createProfessor(prof1);
		profServ.createProfessor(prof2);
		profServ.createProfessor(prof3);
	}

	@Test
	@Order(2)
	void checkProfessorsListTest() {
		assertNotNull(profServ.getProfessorById(1L));
		assertNotNull(profServ.getProfessorById(2L));
		assertNotNull(profServ.getProfessorById(3L));
		
		assertEquals("Pirmas Destytojas", profServ.getProfessorById(1L).getFullName());
		assertEquals("Antras Destytojas", profServ.getProfessorById(2L).getFullName());
		assertEquals("trecias@mail.com", profServ.getProfessorById(3L).getEmail());
	}

	@Test
	@Order(3)
	@Transactional
	void updateProfessorTest() {
		Professor prof1 = profServ.getProfessorById(1L);
		prof1.setFullName("Naujas Vardas");
		profServ.updateProfessor(1L, prof1);
		
		Professor prof2 = profServ.getProfessorById(2L);
		prof2.setEmail("profas@mail.com");
		profServ.updateProfessor(2L, prof2);
		
		Professor prof3 = profServ.getProfessorById(3L);
		prof3.setEmail("naujas@mail.com");
		profServ.updateProfessor(3L, prof3);
		
		assertEquals("Naujas Vardas", profServ.getProfessorById(1L).getFullName());
		assertEquals("profas@mail.com", profServ.getProfessorById(2L).getEmail());
		assertEquals("naujas@mail.com", profServ.getProfessorById(3L).getEmail());
	}

	@Test
	@Order(4)
	void deleteProfessorTest() {
		profServ.deleteProfessor(1L);
		
		assertFalse(profRep.existsById(1L));
		
		profServ.deleteProfessor(2L);
		profServ.deleteProfessor(3L);
		
		assertTrue(profRep.findAll().isEmpty());
	}
}
