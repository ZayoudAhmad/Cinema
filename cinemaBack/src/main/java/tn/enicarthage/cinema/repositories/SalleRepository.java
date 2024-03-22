package tn.enicarthage.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.enicarthage.cinema.entities.Salle;


@RepositoryRestResource
public interface SalleRepository extends JpaRepository<Salle,Long>{

}
