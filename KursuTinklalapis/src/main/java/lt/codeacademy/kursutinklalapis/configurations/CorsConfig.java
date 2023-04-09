//package lt.codeacademy.kursutinklalapis.configurations;
//
//import java.io.IOException;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Configuration
//public class CorsConfig implements Filter {
//	@Configurationpublic
//	class CorsConfig {
//		@Bean
//		public CorsFilter corsFilter(@Value("${app.cors.allowed-origins}") List<String> allowedOrigins) {
//			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//			CorsConfiguration config = new CorsConfiguration();
//			config.setAllowCredentials(true);
//			config.setAllowedOriginPatterns(allowedOrigins);
//			config.addAllowedMethod("*");
//			config.addAllowedHeader("*");
//			source.registerCorsConfiguration("/**", config);
//			return new CorsFilter(source);
//		}
//	}
//
//}
