package ua.step.debating.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;




@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String text;
	@ManyToOne
	private Lobby Lobby;
	private Date date;
	@ManyToOne
	private User author;
	@ManyToMany
	private List<User> listOfLikes;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Lobby getLobby() {
		return Lobby;
	}
	public void setLobby(Lobby lobby) {
		Lobby = lobby;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public List<User> getListOfLikes() {
		return listOfLikes;
	}
	public void setListOfLikes(List<User> listOfLikes) {
		this.listOfLikes = listOfLikes;
	}

	
	
}
