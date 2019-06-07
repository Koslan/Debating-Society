package ua.step.debating.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LobbyStatistics")
public class LobbyStatistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private Integer debatCount;
	
	private Integer discussionCount;
	
	@ManyToMany
	private List<Lobby> listOfLobbyDebat;
	
	@ManyToMany
	private List<Lobby> listOfLobbyDiscussion;
	
	@OneToMany
	private List<Lobby> listOfMyCreatedDebat; //список дискуссий которые я создал;
	
	@OneToMany
	private List<Lobby> listOfMyCreatedDiscussion; //список дебатов которые я создал;
	
	@OneToMany
	private List<Message> message;
	
	@OneToMany
	private List<User> listOfSpendedPointsForUsers;  // список на что я потратил баллов
	
	@OneToMany
	private List<Message> listOfSpendedPointsForMessage;  // список на что я потратил баллы
	
	LobbyStatistics() {}
	
	public Integer getDebatCount() {
		return debatCount;
	}
	
	public void setDebatCount(Integer debatCount) {
		this.debatCount = debatCount;
	}
	
	public Integer getDiscussionCount() {
		return discussionCount;
	}
	
	public void setDiscussionCount(Integer discussionCount) {
		this.discussionCount = discussionCount;
	}
	
	public List<Lobby> getListOfLobbyDebat() {
		return listOfLobbyDebat;
	}
	
	public void setListOfLobbyDebat(List<Lobby> listOfLobbyDebat) {
		this.listOfLobbyDebat = listOfLobbyDebat;
	}
	
	public List<Lobby> getListOfLobbyDiscussion() {
		return listOfLobbyDiscussion;
	}
	
	public void setListOfLobbyDiscussion(List<Lobby> listOfLobbyDiscussion) {
		this.listOfLobbyDiscussion = listOfLobbyDiscussion;
	}
	
	public List<Lobby> getListOfMyCreatedDebat() {
		return listOfMyCreatedDebat;
	}
	
	public void setListOfMyCreatedDebat(List<Lobby> listOfMyCreatedDebat) {
		this.listOfMyCreatedDebat = listOfMyCreatedDebat;
	}
	
	public List<Lobby> getListOfMyCreatedDiscussion() {
		return listOfMyCreatedDiscussion;
	}
	
	public void setListOfMyCreatedDiscussion(List<Lobby> listOfMyCreatedDiscussion) {
		this.listOfMyCreatedDiscussion = listOfMyCreatedDiscussion;
	}
	
	public List<Message> getMessage() {
		return message;
	}
	
	public void setMessage(List<Message> message) {
		this.message = message;
	}
	
	public List<User> getListOfSpendedPointsForUsers() {
		return listOfSpendedPointsForUsers;
	}
	
	public void setListOfSpendedPointsForUsers(List<User> listOfSpendedPointsForUsers) {
		this.listOfSpendedPointsForUsers = listOfSpendedPointsForUsers;
	}
	
	public List<Message> getListOfSpendedPointsForMessage() {
		return listOfSpendedPointsForMessage;
	}
	public void setListOfSpendedPointsForMessage(List<Message> listOfSpendedPointsForMessage) {
		this.listOfSpendedPointsForMessage = listOfSpendedPointsForMessage;
	}
	
}
