package lt.codeacademy.kursutinklalapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="registration")
public class Registration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="tutor")
	private Tutor tutor;
	@Column(name="student")
	private Student student;
	
	//registration date ??
	
	public Registration() {}

	public Registration(Long id, Tutor tutor, Student student) {		
		this.id = id;
		this.tutor = tutor;
		this.student = student;
	}

	public Registration(Tutor tutor, Student student) {		
		this.tutor = tutor;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", tutor=" + tutor + ", student=" + student + "]";
	}

}
