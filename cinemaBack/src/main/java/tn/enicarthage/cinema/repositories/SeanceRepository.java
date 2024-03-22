package tn.enicarthage.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.enicarthage.cinema.entities.Seance;


@RepositoryRestResource
public interface SeanceRepository extends JpaRepository<Seance,Long>{

}
