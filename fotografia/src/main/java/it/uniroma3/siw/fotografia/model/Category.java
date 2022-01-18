package it.uniroma3.siw.fotografia.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@OneToMany(mappedBy="category")
	private List<Photography> photographies;
	
	
	public Category() {
		this.photographies = new ArrayList<>();
	}
	
	public Category(Long id, String name, String photos) {
		this();
		this.id = id;
		this.name = name;
		this.photos = photos;
	}

	
	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"category-photos" + "/"+ id + "/" + photos;
    }
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public List<Photography> getPhotographies() {
		return photographies;
	}


	public void setPhotographies(List<Photography> photographies) {
		this.photographies = photographies;
	}
	
	
}
