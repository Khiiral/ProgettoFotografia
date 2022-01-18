package it.uniroma3.siw.fotografia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.fotografia.model.Category;
import it.uniroma3.siw.fotografia.repository.CategoryRepository;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PhotographyService photographyService;
	
	
	@Transactional
	public Category insert(Category category) {
		return categoryRepository.save(category);
	}
	
	@Transactional
	public List<Category> categoryByName(String name){
		return categoryRepository.findByName(name);
	}
	
	@Transactional
	public List<Category> getAllCategory(){
		return (List<Category>) categoryRepository.findAll();
	}
	
	@Transactional
	public Category categoryById(Long id) {
		Optional<Category> optional = categoryRepository.findById(id);
		if(optional.isPresent()) 
			return optional.get();
		else
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(Category category) {
		List<Category> categories = this.categoryRepository.findByName(category.getName());
		if (categories.size() > 0)
			return true;
		else 
			return false;
	}
	
	public PhotographyService getPhotographyService() {
		return photographyService;
	}
	
	@Transactional
	public void deleteCategory(Long id) {
		this.categoryRepository.deleteById(id);	
	}
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
