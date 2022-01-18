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

import it.uniroma3.siw.fotografia.controller.validator.CategoryValidator;
import it.uniroma3.siw.fotografia.model.Category;
import it.uniroma3.siw.fotografia.service.CategoryService;
import it.uniroma3.siw.fotografia.upload.FileUploadUtil;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryValidator categoryValidator;
	
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryForm";
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String getCategory(@PathVariable("id") Long id, Model model) {
		Category category = this.categoryService.categoryById(id);
		model.addAttribute("category", category);
		model.addAttribute("photographies", category.getPhotographies());
		model.addAttribute("role", this.categoryService.getCredentialsService().getRoleAuthenticated());
		return "category";
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String getCategories(Model model) {
		model.addAttribute("categories", this.categoryService.getAllCategory());
		model.addAttribute("role", this.categoryService.getCredentialsService().getRoleAuthenticated());
		return "categories";
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	  public String addCategory(@ModelAttribute("category") Category category,@RequestParam("image") MultipartFile multipartFile, 
	  									Model model, BindingResult bindingResult)throws IOException {
	  	this.categoryValidator.validate(category, bindingResult);
	      if (!bindingResult.hasErrors()) {
	      	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	      	category.setPhotos(fileName);
	      	
	      	Category savedCategory =this.categoryService.insert(category);
	      	
	      	String uploadDir = "category-photos/" + savedCategory.getId();
	      	
	      	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	      	model.addAttribute("categories", this.categoryService.getAllCategory());
	      	model.addAttribute("role", this.categoryService.getCredentialsService().getRoleAuthenticated());
	      	
	      	return "redirect:/category";
	      	}
	        return "categoryForm";
	}
}
