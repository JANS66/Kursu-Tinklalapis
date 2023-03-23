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

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "professor_name")
	private String professorName;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Registration> registrations = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Course() {
	}

	public Course(String name, String description, String professorName) {
		this.name = name;
		this.description = description;
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

}
