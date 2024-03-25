package tn.enicarthage.cinema.entities;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Projection {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Date dateProjection;
	double prix;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	Salle salle;
	@ManyToOne
	Film film;
	@OneToMany(mappedBy = "projection")
	@JsonProperty(access = Access.WRITE_ONLY)
	List<Ticket> tickets;
	@ManyToOne
	Seance seance;
}
