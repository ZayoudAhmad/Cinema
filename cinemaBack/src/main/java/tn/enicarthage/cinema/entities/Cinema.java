package tn.enicarthage.cinema.entities;

import java.util.List;

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
public class Cinema {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String nom;
	double longitude;
	double latitude;
	double altitude;
	int nombreSalles;
	@OneToMany(mappedBy = "cinema")
	private List<Salle> salles;
	@ManyToOne
	Ville ville;
}
