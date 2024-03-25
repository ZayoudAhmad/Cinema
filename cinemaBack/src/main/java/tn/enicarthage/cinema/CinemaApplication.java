package tn.enicarthage.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;

import tn.enicarthage.cinema.entities.Film;
import tn.enicarthage.cinema.services.ICinemaInitService;

@SpringBootApplication
@CrossOrigin("*")
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ICinemaInitService cinemaInitService, RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Film.class);
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
