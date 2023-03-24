package lt.codeacademy.kursutinklalapis.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String fullName;

	@Column(name = "email")
	private String email;
	
	public Professor() {}
	
	public Professor(Long id, String fullName, String email) {		
		this.id = id;
		this.fullName = fullName;
		this.email = email;
	}

	public Professor(String fullName, String email) {
		this.fullName = fullName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", fullName=" + fullName + ", email=" + email + "]";
	}
	
}