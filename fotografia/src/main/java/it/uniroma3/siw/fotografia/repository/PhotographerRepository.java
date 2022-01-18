package it.uniroma3.siw.fotografia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.fotografia.model.Photographer;

public interface PhotographerRepository extends CrudRepository<Photographer, Long> {

	public List<Photographer> findByName(String name);
	
	public List<Photographer> findByNameAndSurname(String name, String surname);
	
}
