package lt.codeacademy.kursutinklalapis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

	// Operacijos kurias gali pasiekti/atlikti is Main Puslapio
	@GetMapping("/")
	public String getMainPage() {
		return "main/mainPage";
	}
	
	// Prijungti nauja vartotoja
	@PostMapping("/signup")
	public String createNewUserPage() {
		return "main/signUp";
	}
	
	// Prisijungti su esamu vartotoju prie tinklapio
	@GetMapping("/login")
	public String loginPage() {
		return "main/login";
	}
	
	// Perziureti kokius kursus galima lankyti
	@GetMapping("/availableCourses")
	public String checkAvailableCourses() {
		return "main/availableCourses";
	}
	
	//---------------------------------------------\\
	// Operacijos kurias gali pasiekti/atlikti kai prisijungi prie tinklapio
	@GetMapping("/checkUserCourses")
	public String checkCoursesPage() {
		return "user/checkUserCourses";
	}
	
	//---------------------------------------------\\
	// Puslapiai kuriuose gali redaguoti kursus
	@PostMapping("/checkUserCourses/addUserCourses")
	public String addCourses() {
		return "user/edit/addUserCourses";
	}
	
	@DeleteMapping("/checkUserCourses/deleteUserCourses")
	public String deleteCourses() {
		return "deleteCourses";
	}
	
}
























