package lt.codeacademy.kursutinklalapis.entities;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class RoleDeserializer extends JsonDeserializer<Role> {
	@Override
	public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String value = p.getValueAsString();
		if (value.equalsIgnoreCase("admin")) {
			return Role.ADMIN;
		} else if (value.equalsIgnoreCase("professor")) {
			return Role.PROFESSOR;
		} else {
			return Role.STUDENT;
		}
	}
}
