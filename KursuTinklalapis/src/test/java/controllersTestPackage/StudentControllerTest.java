package controllersTestPackage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import lt.codeacademy.kursutinklalapis.KursuTinklalapisApplication;
import lt.codeacademy.kursutinklalapis.controllers.StudentController;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.services.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studServ;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getAllStudentsTest() throws Exception {
		Student st1 = new Student(1L, "Vardas", "Pavarde", "email@mail.com");
		Student st2 = new Student(2L, "Vardenis", "Pavardenis", "pastas@mail.com");

		List<Student> allStudent = List.of(st1, st2);

		when(studServ.getAllStudents()).thenReturn(allStudent);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/allstudents")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Vardas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Pavarde"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("email@mail.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Vardenis"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Pavardenis"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("pastas@mail.com"));
	}

	@Test
	public void getStudentByIdTest() throws Exception {
		Student st1 = new Student(1L, "Vardas", "Pavarde", "email@mail.com");
		Student st2 = new Student(2L, "Vardenis", "Pavardenis", "pastas@mail.com");

		when(studServ.getStudentById(2L)).thenReturn(st2);
		when(studServ.getStudentById(1L)).thenReturn(st1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/getstudent/{id}", 2))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Vardenis"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Pavardenis"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("pastas@mail.com"));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/getstudent/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Vardas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Pavarde"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@mail.com"));
	}

	@Test
	public void createStudentTest() throws Exception {
		Student st = new Student(1L, "Vardas", "Pavarde", "email@mail.com");
		when(studServ.createStudent(ArgumentMatchers.any(Student.class))).thenReturn(st);

		String newStudent = "{\"id\":\"1\"," + "\"firstName\":\"Vardas\"," + "\"lastName\":\"Pavarde\","
				+ "\"email\":\"email@mail.com\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/register").contentType(MediaType.APPLICATION_JSON)
				.content(newStudent)).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Vardas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Pavarde"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@mail.com"));

	}

	@Test
	public void updateStudentTest() throws Exception {
		
		
		Student student = new Student(1L, "V", "P", "E");

		Mockito.when(studServ.getStudentById(Mockito.any())).thenReturn(student);
		Mockito.when(studServ.updateStudent(Mockito.any(), any(Student.class))).thenReturn(student);
		
		 String studentJson = objectMapper.writeValueAsString(student);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/updatestudent/1")
				.content(studentJson)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("V"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("P"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("E"));

	}

	@Test
	public void deleteStudentTest() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/deletestudent/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

}
