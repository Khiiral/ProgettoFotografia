package it.uniroma3.siw.fotografia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Photography {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	@Column(nullable=false, length=4)
	private Integer year;
	
	private String place;
	
	@Column(nullable = true, length = 64)
    private String photos;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Photographer photographer;
	
	
	public Photography() {
		
	}
	
	public Photography(Long id, String title, Integer year, String place, String photos) {
		this();
		this.id = id;
		this.title = title;
		this.year = year;
		this.place = place;
		this.photos = photos;
	}


	@Transient
    public String getPhotosImagePath() {
        if (this.getPhotos() == null || this.getId() == null) return null;
         
        return "/"+"photography-photos" + "/"+ id + "/" + photos;
    }
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getPhotos() {
		return photos;
	}


	public void setPhotos(String photos) {
		this.photos = photos;
	}


	public Photographer getPhotographer() {
		return photographer;
	}


	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
