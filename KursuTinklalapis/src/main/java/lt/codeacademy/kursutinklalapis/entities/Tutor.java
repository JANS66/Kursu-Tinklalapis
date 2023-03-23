package lt.codeacademy.kursutinklalapis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tutors")
public class Tutor {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(name="first_name")
		private String firstName;
		@Column(name="last_name")
		private String lastName;
		@Column(name="subject")
		private Subject name;
		
		public Tutor() {}

		public Tutor(Long id, String firstName, String lastName, Subject name) {		
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.name = name;
		}

		public Tutor(String firstName, String lastName, Subject name) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Tutor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", name=" + name + "]";
		}
}
