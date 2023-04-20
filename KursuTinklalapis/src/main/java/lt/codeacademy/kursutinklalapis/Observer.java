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
//		if (userRep.findAll().isEmpty()) {
//			seedUserDummyData();
//			seedDummyData();
//		}
//		if (professorRep.findAll().isEmpty())
//			seedProfessorDummyData();
//		if (courseRep.findAll().isEmpty())
//			seedCoursesDummyData();

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
				new Course("Socialiniai mokslai", "Ekonomika", "Nerijus Maciulis"),
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
		List<User> users = List.of(
				new User("Adminas", "Adminauskas", "admin@admin.lt", encoder.encode("admin"), Role.ADMIN),
				new User("Petronijus", "Petrauskas", "professor@professor.lt", encoder.encode("professor"),
						Role.PROFESSOR),
				new User("Mokytas", "Mokinys", "moksliukas@mail.lt", encoder.encode("12345678"), Role.STUDENT));
		userRep.saveAll(users);
	}
}
