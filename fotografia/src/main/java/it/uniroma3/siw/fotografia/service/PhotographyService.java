package it.uniroma3.siw.fotografia.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.fotografia.model.Photography;
import it.uniroma3.siw.fotografia.repository.PhotographyRepository;




@Service
public class PhotographyService {

	@Autowired
	private PhotographyRepository photographyRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PhotographerService photographerService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	
	@Transactional
	public Photography insert(Photography photography) {
		return photographyRepository.save(photography);
	}
	
	@Transactional
	public List<Photography> photographyByTitle(String title) {
		return photographyRepository.findByTitle(title);
	}

	@Transactional
	public List<Photography> getAllPhotographies() {
		return (List<Photography>) photographyRepository.findAll();
	}

	@Transactional
	public Photography photographyById(Long id) {
		Optional<Photography> optional = photographyRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public boolean deletePhotography(Long id) {
		try {
			this.photographyRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Transactional
	public boolean alreadyExists(Photography photography) {
		List<Photography> photographies = this.photographyRepository.findByTitle(photography.getTitle());
		if (photographies.size() > 0)
			return true;
		else 
			return false;
	}
	
	public CategoryService getCategoryService() {
		return this.categoryService;
	}

	public PhotographerService getPhotographerService() {
		return this.photographerService;
	}
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
