package tn.enicarthage.cinema.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.jayway.jsonpath.internal.function.text.Length;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import tn.enicarthage.cinema.entities.Categorie;
import tn.enicarthage.cinema.entities.Cinema;
import tn.enicarthage.cinema.entities.Film;
import tn.enicarthage.cinema.entities.Place;
import tn.enicarthage.cinema.entities.Projection;
import tn.enicarthage.cinema.entities.Salle;
import tn.enicarthage.cinema.entities.Seance;
import tn.enicarthage.cinema.entities.Ticket;
import tn.enicarthage.cinema.entities.Ville;
import tn.enicarthage.cinema.repositories.CategorieRepository;
import tn.enicarthage.cinema.repositories.CinemaRepository;
import tn.enicarthage.cinema.repositories.FilmRepository;
import tn.enicarthage.cinema.repositories.PlaceRepository;
import tn.enicarthage.cinema.repositories.ProjectionRepository;
import tn.enicarthage.cinema.repositories.SalleRepository;
import tn.enicarthage.cinema.repositories.SeanceRepository;
import tn.enicarthage.cinema.repositories.TicketRepository;
import tn.enicarthage.cinema.repositories.VilleRepository;

@Service
@Transactional
@AllArgsConstructor
public class CinemaInitServiceImpl implements ICinemaInitService {

	private VilleRepository villeRepository;
	private CinemaRepository cinemaRepository;
	private SalleRepository salleRepository;
	private SeanceRepository seanceRepository;
	private PlaceRepository placeRepository;
	private CategorieRepository categorieRepository;
	private FilmRepository filmRepository;
	private ProjectionRepository projectionRepository;
	private TicketRepository ticketRepository;
	
	@Override
	public void initVilles() {
		Stream.of("Tunis", "Sousse", "Sfax", "Djerba").forEach(v->{
			Ville ville = new Ville();
			ville.setNom(v);
			villeRepository.save(ville);
		});
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("Pathe", "Agora", "Palace", "Parma")
			.forEach(nom -> {
				Cinema cinema = new Cinema();
				cinema.setNom(nom);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
		});

	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for (int i = 0; i < cinema.getNombreSalles(); i++) {
				Salle salle = new Salle();
				salle.setNom("Salle" + (i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(15+(int)(Math.random()*10));
				salleRepository.save(salle);
			}
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for (int i = 0; i < salle.getNombrePlace(); i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
		
	}
	
	@Override
	public void initSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Histoire", "Actions", "Fiction", "Drama").forEach(cat ->{
			Categorie categorie = new Categorie();
			categorie.setNom(cat);
			categorieRepository.save(categorie);
		});
		
	}

	@Override
	public void initFilms() {
		double[] durees = new double[] {1,1.5,2,2.5,3};
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("Forrest Gump", "V for Vendetta", "Avengers Endgame", "Joker", "Gladiator")
		.forEach(f -> {
			Film film = new Film();
			film.setTitre(f);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(f.replaceAll(" ", "")); 
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
	}

	@Override
	public void initProjections() {
		double[] prix = new double[] {30,50,60,70,90,100};
		villeRepository.findAll().forEach(ville -> {
			ville.getCinemas().forEach(cinema ->{
				cinema.getSalles().forEach(salle ->{
					filmRepository.findAll().forEach(film ->{
						seanceRepository.findAll().forEach(seance ->{
							Projection projection = new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setPrix(prix[new Random().nextInt(prix.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionRepository.save(projection);
						});
					});
				});
			});
		});
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place -> {
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
			});
		});
	}

}
