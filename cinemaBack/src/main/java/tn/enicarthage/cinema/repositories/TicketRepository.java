package tn.enicarthage.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.enicarthage.cinema.entities.Ticket;


@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket,Long>{

}
