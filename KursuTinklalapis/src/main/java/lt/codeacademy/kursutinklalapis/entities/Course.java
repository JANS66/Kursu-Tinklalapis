package lt.codeacademy.kursutinklalapis.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "subject")
	private String subject;

	@Column(name = "description")
	private String description;

	@Column(name = "professor_name")
	private String professorName;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Registration> registrations = new HashSet<>();
	
	public Course() {}
	
	public Course(Long id, String subject, String description, String professorName, Set<Registration> registrations) {		
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.professorName = professorName;
		this.registrations = registrations;
	}

	public Course(String subject, String description, String professorName) {
		this.subject = subject;
		this.description = description;
		this.professorName = professorName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
	public void setRegistrations(Registration registration) {
		registrations.add(registration);
		registration.setCourse(this);
	}

	public void removeRegistration(Registration registration) {
		registrations.remove(registration);
		registration.setCourse(null);
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", subject=" + subject + ", description=" + description + ", professorName="
				+ professorName + ", registrations=" + registrations + "]";
	}

}
