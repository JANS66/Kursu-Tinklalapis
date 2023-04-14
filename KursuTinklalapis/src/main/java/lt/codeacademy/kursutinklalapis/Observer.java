package lt.codeacademy.kursutinklalapis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.entities.User;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.repositories.ProfessorRepository;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Observer {

	@Autowired
	UserRepository userRep;

	@Autowired
	CourseRepository courseRep;

	@Autowired
	ProfessorRepository professorRep;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		if (userRep.findAll().isEmpty()) {
			seedUserDummyData();
			seedDummyData();
		}
		if (professorRep.findAll().isEmpty())
			seedProfessorDummyData();
		if (courseRep.findAll().isEmpty())
			seedCoursesDummyData();

	}

	private void seedUserDummyData() {
		List<User> students = List.of(new User("Jonas", "Petraitis", "jonas@mail.com", "$2a$10$qA", Role.STUDENT),
				new User("Petras", "Antanaitis", "petras@mail.com", "$2a$10$qA", Role.STUDENT),
				new User("Antanas", "Jonaitis", "antanas@mail.com", "$2a$10$qA", Role.STUDENT));

		userRep.saveAll(students);

	}

	private void seedProfessorDummyData() {
		List<Professor> professors = List.of(new Professor("Pitagoras", "Pitagoras@mail.com"),
				new Professor("Rovanas Atkinsonas", "Bynas@mail.com"),
				new Professor("Albertas Einsteinas", "Fizika@mail.com"),
				new Professor("Herodotas", "Istorija@mail.com"),
				new Professor("Algimantas Cekuolis", "Cekuolis@mail.com"),
				new Professor("Kristupas Kolumbas", "Amerika@mail.com"));
		professorRep.saveAll(professors);
	}

	private void seedCoursesDummyData() {

		List<Course> courses = List.of(new Course("Tikslieji mokslai", "Matematika", "Pitagoras"),
				new Course("Tikslieji mokslai", "Fizika", "Albertas Einsteinas"),
				new Course("Tikslieji mokslai", "Chemija", "Dimitrijus Mendelejevas"),
				new Course("Tikslieji mokslai", "Informacines technologijos", "Vaidas Cesonis"),
				new Course("Socialiniai mokslai", "Ekonomika", "Nerijus Maxiulis"),
				new Course("Socialiniai mokslai", "Teise", "Vilija Venslovaite"),
				new Course("Socialiniai mokslai", "Psichologija", "Rasa Barkauskiene"),
				new Course("Socialiniai mokslai", "Istorija", "Herodotas"),
				new Course("Humanitariniai mokslai", "Menotyra", "Rovanas Atkinsonas"),
				new Course("Humanitariniai mokslai", "Filosofija", "Leonidas Donskis"),
				new Course("Gamtos mokslai", "Geografija", "Kristupas Kolumbas"),
				new Course("Gamtos mokslai", "Biologija", "Carlzas Darvinas"));
		courseRep.saveAll(courses);
	}

	private void seedDummyData() {

		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setFirstname("Adminas");
		user.setLastname("Adminauskas");
		user.setEmail("admin@admin.lt");
		user.setPassword(encoder.encode("admin"));
		user.setRole(Role.ADMIN);
		;
		userRep.save(user);

		User user2 = new User();
		user2.setFirstname("Petronijus");
		user2.setLastname("Petrauskas");
		user2.setEmail("professor@professor.lt");
		user2.setPassword(encoder.encode("professor"));
		user2.setRole(Role.PROFESSOR);
		userRep.save(user2);

		User user3 = new User();
		user3.setFirstname("Mokytas");
		user3.setLastname("Mokinys");
		user3.setEmail("moksliukas@mail.lt");
		user3.setPassword(encoder.encode("12345678"));
		user3.setRole(Role.STUDENT);
		userRep.save(user3);
	}
}
