package br.com.gustavodepaula.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class BibliotecaApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
}
