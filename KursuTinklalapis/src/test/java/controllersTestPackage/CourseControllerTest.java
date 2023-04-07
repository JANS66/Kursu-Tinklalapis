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
import lt.codeacademy.kursutinklalapis.controllers.CourseController;
import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.services.CourseService;

@RunWith(SpringRunner.class)
@WebMvcTest(CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = KursuTinklalapisApplication.class)
class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService cServ;

	@Test
	public void getAllCoursesTest() throws Exception {
		Course c1 = new Course(1L, "Fizika", "Fiziniai desniai", "Fizikas");
		Course c2 = new Course(2L, "Matematika", "Algebra", "Matematikas");

		List<Course> allCourses = List.of(c1, c2);

		when(cServ.getAllCourses()).thenReturn(allCourses);

		mockMvc.perform(MockMvcRequestBuilders.get("/course/allcourses"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].subject").value("Fizika"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Fiziniai desniai"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].professorName").value("Fizikas"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].subject").value("Matematika"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("Algebra"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].professorName").value("Matematikas"));

	}

	@Test
	public void getCourseByIdTest() throws Exception {
		Course c1 = new Course(1L, "Fizika", "Fiziniai desniai", "Fizikas");
		Course c2 = new Course(2L, "Matematika", "Algebra", "Matematikas");

		when(cServ.getCourseById(2L)).thenReturn(c2);
		when(cServ.getCourseById(1L)).thenReturn(c1);

		mockMvc.perform(MockMvcRequestBuilders.get("/course/{id}", 2))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.subject").value("Matematika"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Algebra"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.professorName").value("Matematikas"));

		mockMvc.perform(MockMvcRequestBuilders.get("/course/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.subject").value("Fizika"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Fiziniai desniai"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.professorName").value("Fizikas"));
	}

	@Test
	public void createCourseTest() throws Exception {
		Course cr = new Course(2L, "Matematika", "Algebra", "Matematikas");
		when(cServ.saveCourse(ArgumentMatchers.any(Course.class))).thenReturn(cr);

		String newCourse = "{\"id\":\"2\"," + "\"subject\":\"Matematika\"," + "\"description\":\"Algebra\","
				+ "\"professorName\":\"Matematikas\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/course/creatcourse").contentType(MediaType.APPLICATION_JSON)
				.content(newCourse))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.subject").value("Matematika"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Algebra"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.professorName").value("Matematikas"));

	}

	@Test
	@Disabled
	public void updateStudentTest() throws Exception {

	}

	@Test
	public void deleteStudentTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/course/{id}", 2L)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
