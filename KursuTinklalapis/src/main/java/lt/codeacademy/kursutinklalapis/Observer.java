package lt.codeacademy.kursutinklalapis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.entities.User;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.repositories.ProfessorRepository;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class Observer {

	@Autowired
	StudentRepository studentRep;

	@Autowired
	CourseRepository courseRep;

	@Autowired
	ProfessorRepository professorRep;
	
	@Autowired
	UserRepository userRep;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		if(studentRep.findAll().isEmpty())
		seedStudentDummyData();
		if(professorRep.findAll().isEmpty())
		seedProfessorDummyData();
		if(courseRep.findAll().isEmpty())
		seedCoursesDummyData();
		if(userRep.findAll().isEmpty())
		seedUserDummyData();
	}

	private void seedStudentDummyData() {

		List<Student> students = List.of(new Student("Jonas", "Petraitis", "Jonas@mail.com"),
				new Student("Petras", "Antanaitis", "Petras@mail.com"),
				new Student("Antanas", "Jonaitis", "Antanas@mail.com"));
		
		studentRep.saveAll(students);

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

		List<Course> courses = List.of(new Course("Matematika", "Algebra", professorRep.findAll().get(0).getFullName()),
				new Course("Anglu k", "Anglu k. pagrindai", professorRep.findAll().get(1).getFullName()),
				new Course("Fizika", "Fizikos desniu mokslas", professorRep.findAll().get(2).getFullName()),
				new Course("Istorija", "Istorijos mokslas", professorRep.findAll().get(3).getFullName()),
				new Course("Lietuviu", "Lietuviu kalbos pagrindai", professorRep.findAll().get(4).getFullName()),
				new Course("Geografija", "Geografijos mokslas", professorRep.findAll().get(5).getFullName()));

		courseRep.saveAll(courses);

	}
	
	private void seedUserDummyData() {
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@admin.lt");
		user.setPassword( encoder.encode("admin"));
		user.setRole(Role.ADMIN);;
		userRep.save(user);
		
		User user2 = new User();
		user2.setUsername("professor");
		user2.setEmail("professor@professor.lt");
		user2.setPassword( encoder.encode("professor") );
		user2.setRole(Role.PROFESSOR);
		userRep.save(user2);
		
	}

}
