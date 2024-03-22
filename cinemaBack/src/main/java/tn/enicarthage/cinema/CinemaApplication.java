package tn.enicarthage.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.enicarthage.cinema.services.ICinemaInitService;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ICinemaInitService cinemaInitService) {
		return args -> {
			cinemaInitService.initVilles();
			cinemaInitService.initCinemas();
			cinemaInitService.initSalles();
			cinemaInitService.initPlaces();
			cinemaInitService.initSeances();
			cinemaInitService.initCategories();
			cinemaInitService.initFilms();
			cinemaInitService.initProjections();
			cinemaInitService.initTickets();
		};
	}

}
