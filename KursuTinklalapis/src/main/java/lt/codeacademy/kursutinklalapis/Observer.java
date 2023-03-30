package lt.codeacademy.kursutinklalapis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import lt.codeacademy.kursutinklalapis.entities.Course;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.entities.Student;
import lt.codeacademy.kursutinklalapis.repositories.CourseRepository;
import lt.codeacademy.kursutinklalapis.repositories.ProfessorRepository;
import lt.codeacademy.kursutinklalapis.repositories.StudentRepository;

@Configuration
public class Observer {

	@Autowired
	StudentRepository studentRep;

	@Autowired
	CourseRepository courseRep;

	@Autowired
	ProfessorRepository professorRep;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
//		seedStudentDummyData();
//		seedProfessorDummyData();
//		seedCoursesDummyData();
	}

	private void seedStudentDummyData() {

		List<Student> students = List.of(
				new Student("Jonas", "Petraitis", "Jonas@mail.com"),
				new Student("Petras", "Antanaitis", "Petras@mail.com"),
				new Student("Antanas", "Jonaitis", "Antanas@mail.com")
				);

		studentRep.saveAll(students);

	}

	private void seedProfessorDummyData() {

		List<Professor> professors = List.of(
				new Professor("Pitagoras", "Pitagoras@mail.com"),
				new Professor("Rovanas Atkinsonas", "Bynas@mail.com"),
				new Professor("Albertas Einsteinas", "Fizika@mail.com"),
				new Professor("Herodotas", "Istorija@mail.com"),
				new Professor("Algimantas Cekuolis", "Cekuolis@mail.com"),
				new Professor("Kristupas Kolumbas", "Amerika@mail.com")
				);

		professorRep.saveAll(professors);
	}

	private void seedCoursesDummyData() {

		List<Course> courses = List.of(
				new Course("Matematika", "Algebra", professorRep.findAll().get(0).getFullName()),
				new Course("Anglu k", "Anglu k. pagrindai", professorRep.findAll().get(1).getFullName()),
				new Course("Fizika", "Fizikos desniu mokslas", professorRep.findAll().get(2).getFullName()),
				new Course("Istorija", "Istorijos mokslas", professorRep.findAll().get(3).getFullName()),
				new Course("Lietuviu", "Lietuviu kalbos pagrindai", professorRep.findAll().get(4).getFullName()),
				new Course("Geografija", "Geografijos mokslas", professorRep.findAll().get(5).getFullName())
				);

		courseRep.saveAll(courses);

	}

}
