package lt.codeacademy.kursutinklalapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class KursuTinklalapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursuTinklalapisApplication.class, args);
	}

}

