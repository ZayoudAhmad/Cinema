package tn.enicarthage.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import tn.enicarthage.cinema.entities.Film;
import tn.enicarthage.cinema.entities.Ticket;
import tn.enicarthage.cinema.repositories.FilmRepository;
import tn.enicarthage.cinema.repositories.TicketRepository;

@RestController
@AllArgsConstructor
public class CinemaRestController {
	
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(path="imageFilm/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id") Long id) throws Exception {
		Film film = filmRepository.findById(id).get();
		String photoName = film.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName+".jpg");
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
		List<Ticket> listTickets = new ArrayList<>();
		ticketForm.getTickets().forEach(id -> {
			Ticket ticket = ticketRepository.findById(id).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReservee(true);
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}
}

@Data
class TicketForm {
	private String nomClient;
	private int codePayement;
	private List<Long> tickets = new ArrayList<>();
}
