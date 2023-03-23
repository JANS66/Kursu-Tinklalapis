package lt.codeacademy.kursutinklalapis.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tutors")
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Tutor() {
	}

	public Tutor(String name, String email) {
		this.name = name;
		this.email = email;
	}
}
