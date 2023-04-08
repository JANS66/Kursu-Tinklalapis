package lt.codeacademy.kursutinklalapis.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professors")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Column(name = "email")
	private String email;

//	@Column(name = "fullName")
	private String fullName;

	public Professor(String email, String fullName) {
		super();
		this.email = email;
		this.fullName = fullName;
	}
	
}
