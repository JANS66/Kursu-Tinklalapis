package lt.codeacademy.kursutinklalapis.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "registrations")
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties("registrations")
	private Course course;

	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	@JsonIgnoreProperties("registrations")
	private Student student;

	public Registration() {
	}

	public Registration(Long id, Course course, Student student) {
		this.id = id;
		this.course = course;
		this.student = student;
	}

	public Registration(Course course, Student student) {
		this.course = course;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", course=" + course + ", student=" + student + "]";
	}

}
