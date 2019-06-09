package ua.step.debating.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Dmitrij
 * 
 * Description: Класс Sphere описывает предметную область (сферу), т.е. элемент каталога, в котором
 *              размещаются соответствующие темы.
 *
 */

@Entity
@Table(name = "Sphere")
public class Sphere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private String name;
	
	@OneToMany
	private List<Theme> themes; // Темы, которые относятся к этой сфере
	
	public Sphere() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}
	
}



