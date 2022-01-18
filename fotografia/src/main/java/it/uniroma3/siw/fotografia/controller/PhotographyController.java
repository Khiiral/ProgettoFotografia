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

import it.uniroma3.siw.fotografia.controller.validator.PhotographyValidator;
import it.uniroma3.siw.fotografia.model.Photography;
import it.uniroma3.siw.fotografia.service.PhotographyService;
import it.uniroma3.siw.fotografia.upload.FileUploadUtil;


@Controller
public class PhotographyController {

	@Autowired
	private PhotographyService photographyService;
	
	@Autowired
	private PhotographyValidator photographyValidator;
	
	
	@RequestMapping(value = "/admin/photography", method = RequestMethod.GET)
	public String addPhotography(Model model) {
		model.addAttribute("photography", new Photography());
		model.addAttribute("categories", this.photographyService.getCategoryService().getAllCategory());
		model.addAttribute("photographers", this.photographyService.getPhotographerService().getAllPhotographers());
		return "photographyForm";
	}
	
	@RequestMapping(value = "/photography/{id}", method = RequestMethod.GET)
	public String getPhotography(@PathVariable("id") Long id, Model model) {
		model.addAttribute("photography", this.photographyService.photographyById(id));
		model.addAttribute("role", this.photographyService.getCredentialsService().getRoleAuthenticated());
		return "photography";
	}
	
	@RequestMapping(value = "/photography", method = RequestMethod.GET)
	public String getPhotographies(Model model) {
		model.addAttribute("photographies", this.photographyService.getAllPhotographies());
		model.addAttribute("role", this.photographyService.getCredentialsService().getRoleAuthenticated());
		return "photographies";
	}
	
	@RequestMapping(value = "/admin/deletePhotography/{id}", method = RequestMethod.GET)
	public String deletePhotography(@PathVariable("id") Long id, Model model) {
		this.photographyService.deletePhotography(id);
		model.addAttribute("photographies", this.photographyService.getAllPhotographies());
		model.addAttribute("role", this.photographyService.getCredentialsService().getRoleAuthenticated());
		return "redirect:/photography";
	}
	
	@RequestMapping(value = "/admin/photography", method = RequestMethod.POST)
	  public String addPhotography(@ModelAttribute("photography") Photography photography,@RequestParam("image") MultipartFile multipartFile, 
	  									Model model, BindingResult bindingResult)throws IOException {
	  	this.photographyValidator.validate(photography, bindingResult);
	      if (!bindingResult.hasErrors()) {
	      	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	      	photography.setPhotos(fileName);
	      	
	      	Photography savedPhotography =this.photographyService.insert(photography);
	      	
	      	String uploadDir = "photography-photos/" + savedPhotography.getId();
	      	
	      	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	      	model.addAttribute("photographies", this.photographyService.getAllPhotographies());
	      	model.addAttribute("role", this.photographyService.getCredentialsService().getRoleAuthenticated());
	      	
	      	return "redirect:/photography";
	      	}
	        return "photographyForm";
	}
}
