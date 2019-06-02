package ua.step.debating.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Theme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String name;
	@ManyToOne
	private List<Theme> subthemes; // подтемы
	@OneToOne
	private User creator;
	@OneToMany
	private List<Lobby>  listOfDebats;  //Список  дебатов
	@ManyToMany
	private List<User>  subscribers;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Theme> getSubthemes() {
		return subthemes;
	}
	public void setSubthemes(List<Theme> subthemes) {
		this.subthemes = subthemes;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public List<Lobby> getListOfDebats() {
		return listOfDebats;
	}
	public void setListOfDebats(List<Lobby> listOfDebats) {
		this.listOfDebats = listOfDebats;
	}
	public List<User> getSubscribers() {
		return subscribers;
	}
	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}

	
	
}
