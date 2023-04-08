package lt.codeacademy.kursutinklalapis.security;

public class SecurityConstants {

    public static final String SECRET_KEY = "bQeThWmZq4t7wzCFJNcRfUjXn2r5u8xADGKaPdSgVkYp3s6v9yBE)";
    public static final int JWT_EXPIRATION = 7200000; 
    public static final int  REFRES_EXPIRATION= 7200000; 
    
    public static final String REGISTER_PATH = "/user/**";
    public static final String GET_COURSES = "/courses/**";
    public static final String GET_PROFESSORS = "/professors/**";
    public static final String GET_STUDENTS = "/students/**";
    public static final String UPDATE_STUDENTS = "/students/*/update";
    public static final String UPDATE_COURSES = "/courses/*/update";
    public static final String UPDATE_PROFESSORS = "/professors/*/update";
    public static final String ADD_COURSES = "/courses";
    public static final String ADD_PROFESSORS = "/professors";
    public static final String ADD_STUDENTS = "/students";
    public static final String DELETE_STUDENTS= "/students/*/delete";
    public static final String DELETE_PROFESSORS = "/professors/*/delete";
    public static final String DELETE_COURSES = "/courses/*/delete";
    public static final String COURSE_RESERVATIONS = "/registrations/**";
  
}
