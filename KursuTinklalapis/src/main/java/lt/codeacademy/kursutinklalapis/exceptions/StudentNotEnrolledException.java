package lt.codeacademy.kursutinklalapis.exceptions;

public class StudentNotEnrolledException extends RuntimeException { 

    private static final long serialVersionUID = 1L;

	public StudentNotEnrolledException(Long studentId, Long courseId) {
        super("The student with id: '" + studentId + "' is not enrolled in the course with id: '" + courseId);
    }
    
}