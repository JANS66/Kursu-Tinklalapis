package controllersTestPackage;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lt.codeacademy.kursutinklalapis.KursuTinklalapisApplication;
import lt.codeacademy.kursutinklalapis.controllers.ProfessorController;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.services.ProfessorService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfessorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
class ProfessorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProfessorService profServ;
	
	@Test
	public void getAllProfessorsTest() throws Exception {
		Professor prof1 = new Professor(1L,"Destytojas", "fizika@mail.com");
		Professor prof2 = new Professor(2L,"Mokytojas", "matematika@mail.com");

		List<Professor> allProfessors = List.of(prof1, prof2);

		when(profServ.getAllProfessors()).thenReturn(allProfessors);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/professors/allprofessors"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName").value("Destytojas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("fizika@mail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].fullName").value("Mokytojas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("matematika@mail.com"));
	}
	
	@Test
	public void getProfessorByIdTest() throws Exception {
		Professor prof1 = new Professor(1L,"Destytojas", "fizika@mail.com");
		Professor prof2 = new Professor(2L,"Mokytojas", "matematika@mail.com");
		
		when(profServ.getProfessorById(2L)).thenReturn(prof2);
		when(profServ.getProfessorById(1L)).thenReturn(prof1);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/professors/{id}", 2))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Mokytojas"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("matematika@mail.com"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/professors/{id}", 1))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Destytojas"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("fizika@mail.com"));
		
	}
	
	@Test
	public void createProfessorTest() throws Exception {
		Professor prof2 = new Professor(2L,"Mokytojas", "matematika@mail.com");
		when(profServ.createProfessor(ArgumentMatchers.any(Professor.class))).thenReturn(prof2);

		String newProfessor = "{\"id\":\"2\"," + "\"fullName\":\"Mokytojas\"," + "\"email\":\"matematika@mail.com\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/professors/createprofessor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newProfessor))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("Mokytojas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("matematika@mail.com"));
	}
	
	@Test
	@Disabled
	public void updateProfessorTest() throws Exception {
		
	}
	
	@Test
	public void deleteProfessorTest() throws Exception {
		 	
		    mockMvc.perform(MockMvcRequestBuilders.delete("/api/professors/{id}", 1)
		            .contentType(MediaType.APPLICATION_JSON))
		            .andExpect(MockMvcResultMatchers.status().isOk());
		            
		            
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
