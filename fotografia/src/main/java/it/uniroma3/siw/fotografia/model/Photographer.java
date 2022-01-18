package it.uniroma3.siw.fotografia.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Photographer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String nationality;
	
	private String birthPlace;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column(nullable = true, length = 64)
	private String photos;
	
	@OneToMany(mappedBy="photographer", cascade = CascadeType.ALL)
	private List<Photography> photographies;
	
	public Photographer() {
		this.photographies = new ArrayList<>();
	}
	
	public Photographer(Long id, String name, String surname, String nationality, String birthPlace, LocalDate dateOfBirth, String photos) {
		this();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.nationality = nationality;
		this.birthPlace = birthPlace;
		this.dateOfBirth = dateOfBirth;
		this.photos = photos;
	}
	
	@Transient
	public String getPhotosImagePath() {
		if (this.getPhotos() == null || this.getId() == null) return null;
		
		return "/"+"photographer-photos" + "/"+ id + "/" + photos;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
