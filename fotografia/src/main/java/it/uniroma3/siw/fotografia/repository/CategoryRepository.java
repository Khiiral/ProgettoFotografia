package it.uniroma3.siw.fotografia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.fotografia.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	public List<Category> findByName(String name);

}
