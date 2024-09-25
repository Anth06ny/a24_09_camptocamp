package org.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesApplication implements CommandLineRunner {

	@Autowired
	MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Movie movie = new Movie(0L, "Toto", 1985);
		movieRepository.save(movie);
	}
}
