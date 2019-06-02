package ua.step.debating.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;




@Entity
public class Message {

	private String text;
	private Lobby Lobby;
	private Date date;
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
