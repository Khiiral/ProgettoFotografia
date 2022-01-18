package it.uniroma3.siw.fotografia.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.fotografia.controller.validator.PhotographerValidator;
import it.uniroma3.siw.fotografia.model.Photographer;
import it.uniroma3.siw.fotografia.service.PhotographerService;
import it.uniroma3.siw.fotografia.upload.FileUploadUtil;

@Controller
public class PhotographerController {

	@Autowired
	private PhotographerService photographerService;
	
	@Autowired
	private PhotographerValidator photographerValidator;
	
	
	@RequestMapping(value = "/admin/photographer", method = RequestMethod.GET)
	public String addPhotographer(Model model) {
		model.addAttribute("photographer", new Photographer());
		return "photographerForm";
	}
	
	@RequestMapping(value = "/photographer/{id}", method = RequestMethod.GET)
	public String getPhotographer(@PathVariable("id") Long id, Model model) {
		Photographer photographer = this.photographerService.photographerById(id);
		model.addAttribute("photographer", photographer);
		model.addAttribute("photographies", photographer.getPhotographies());
		model.addAttribute("role", this.photographerService.getCredentialsService().getRoleAuthenticated());
		return "photographer";
	}
	
	@RequestMapping(value = "/photographer", method = RequestMethod.GET)
	public String getPhotographers(Model model) {
		model.addAttribute("photographers", this.photographerService.getAllPhotographers());
		model.addAttribute("role", this.photographerService.getCredentialsService().getRoleAuthenticated());
		return "photographers";
	}
	
	@RequestMapping(value = "/admin/deletePhotographer/{id}", method = RequestMethod.GET)
	public String deletePhotographer(@PathVariable("id") Long id, Model model) {
		this.photographerService.deletePhotographer(id);
		model.addAttribute("photographers", this.photographerService.getAllPhotographers());
		model.addAttribute("role", this.photographerService.getCredentialsService().getRoleAuthenticated());
		return "redirect:/photographer";
	}
		
	@RequestMapping(value = "/admin/photographer", method = RequestMethod.POST)
	  public String addPhotographer(@ModelAttribute("photographer") Photographer photographer,@RequestParam("image") MultipartFile multipartFile, 
	  									Model model, BindingResult bindingResult)throws IOException {
	  	this.photographerValidator.validate(photographer, bindingResult);
	      if (!bindingResult.hasErrors()) {
	      	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	      	photographer.setPhotos(fileName);
	      	
	      	Photographer savedPhotographer =this.photographerService.insert(photographer);
	      	
	      	String uploadDir = "photographer-photos/" + savedPhotographer.getId();
	      	
	      	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	      	model.addAttribute("photographers", this.photographerService.getAllPhotographers());
	      	model.addAttribute("role", this.photographerService.getCredentialsService().getRoleAuthenticated());
	      	
	      	return "redirect:/photographer";
	      	}
	        return "photographerForm";
	}
}
