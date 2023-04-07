package lt.codeacademy.kursutinklalapis.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	private List<Registration> registrations = new ArrayList<>();

	public Course() {
	}

	public Course(Long id, String subject, String description, String professorName, List<Registration> registrations) {
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
	
	public Course(Long id, String subject, String description, String professorName) {
		super();
		this.id = id;
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

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public void addRegistration(Registration registration) {
		registrations.add(registration);
		registration.setCourse(this);
	}

	public void removeRegistration(Registration registration) {
		registrations.remove(registration);
		registration.setCourse(null);
	}

	@Override
	public String toString() {
		return "\n[Course]: " 
				+ "\n[id] = " + id 
				+ "\n[Subject] = " + subject 
				+ "\n[Description] = " + description 
				+ "\n[ProfessorName] = " + professorName; 
				//+ "\n[Registrations] = " + registrations;
	}

}
