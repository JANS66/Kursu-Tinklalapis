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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.codeacademy.kursutinklalapis.security.token.Token;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "subject")
	private String subject;

//	@Column(name = "description")
	private String description;

//	@Column(name = "professor_name")
	private String professorName;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Registration> registrations = new ArrayList<>();

	public Course(String subject, String description, String professorName) {
		super();
		this.subject = subject;
		this.description = description;
		this.professorName = professorName;
	}

	public void addRegistration(Registration registration) {
		registrations.add(registration);
		registration.setCourse(this);
	}

	public void removeRegistration(Registration registration) {
		registrations.remove(registration);
		registration.setCourse(null);
	}

}
