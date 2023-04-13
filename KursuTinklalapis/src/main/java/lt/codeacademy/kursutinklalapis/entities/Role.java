package lt.codeacademy.kursutinklalapis.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = RoleDeserializer.class)
public enum Role {

	ADMIN, PROFESSOR, STUDENT
}
