package lt.codeacademy.kursutinklalapis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lt.codeacademy.kursutinklalapis.entities.Professor;
import lt.codeacademy.kursutinklalapis.entities.Role;
import lt.codeacademy.kursutinklalapis.entities.User;
import lt.codeacademy.kursutinklalapis.repositories.ProfessorRepository;
import lt.codeacademy.kursutinklalapis.repositories.UserRepository;

@Service
@Transactional
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public List<Professor> getAllProfessors() {
		return professorRepository.findAll();
	}

	public Professor getProfessorById(Long id) {
		return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor not found"));
	}

	public Professor createProfessor(Professor professor) {
		return professorRepository.save(professor);
	}

	public Professor updateProfessor(Long id, Professor professorDetails) {
		Professor professor = professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

		professor.setFullName(professorDetails.getFullName());
		professor.setEmail(professorDetails.getEmail());

		return professorRepository.save(professor);
	}

	public void deleteProfessor(Long id) {
		Professor professor = professorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor not found"));

		professorRepository.delete(professor);
	}

}