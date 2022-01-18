package it.uniroma3.siw.fotografia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.fotografia.model.Photography;

public interface PhotographyRepository extends CrudRepository<Photography, Long> {
	
	public List<Photography> findByTitle(String title);
	
}
