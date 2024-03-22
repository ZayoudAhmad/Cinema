package tn.enicarthage.cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Ticket {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String nomClient;
	double prix;
	@Column(unique = false, nullable = true)
	Integer codePayement;
	boolean reservee;
	@ManyToOne
	Place place;
	@ManyToOne
	Projection projection;
}
