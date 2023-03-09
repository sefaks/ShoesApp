package sefa.example.shoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ShoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoesApplication.class, args);
	}

}
