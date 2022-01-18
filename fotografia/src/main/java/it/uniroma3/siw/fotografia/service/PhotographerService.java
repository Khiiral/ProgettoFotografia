package it.uniroma3.siw.fotografia.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.fotografia.model.Photographer;
import it.uniroma3.siw.fotografia.repository.PhotographerRepository;


@Service
public class PhotographerService {

	@Autowired
	private PhotographerRepository photographerRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	
	@Transactional
	public Photographer insert(Photographer photographer) {
		return photographerRepository.save(photographer);
	}
	
	@Transactional
	public List<Photographer> getAllPhotographers(){
		return (List<Photographer>) photographerRepository.findAll();
	}
	
	@Transactional
	public Photographer photographerById(Long id) {
		Optional<Photographer> optional = photographerRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Photographer photographer) {
		List<Photographer> photographers = this.photographerRepository.findByNameAndSurname(photographer.getName(), photographer.getSurname());
		if (photographers.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public boolean deletePhotographer(Long id) {
		try {
			this.photographerRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
