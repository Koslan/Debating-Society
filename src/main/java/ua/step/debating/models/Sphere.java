package ua.step.debating.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Spheres")
public class Sphere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)//не было
	private Integer id;
	private String name;
	@Column(columnDefinition = "TEXT")//добавил
	TalkType talkType;//добавил
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "parent_id")
	private Sphere parent;
	
	@OneToMany(mappedBy = "parent",  orphanRemoval=true)
	private List<Sphere> children;
	
	@OneToMany
	private List<Theme> themes; // Темы, которые относятся к этой сфере

	public TalkType getTalkType() {
		return talkType;
	}

	public void setTalkType(TalkType talkType) {
		this.talkType = talkType;
	}

	public Sphere getParent() {
		return parent;
	}

	public void setParent(Sphere parent) {
		this.parent = parent;
	}

	public List<Sphere> getChildren() {
		return children;
	}

	public void setChildren(List<Sphere> children) {
		this.children = children;
	}
	
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



