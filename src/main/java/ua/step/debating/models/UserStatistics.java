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
@Table(name = "UserStatistics")
public class UserStatistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private Integer reputation;
	
	private Integer activity;
	
	@ManyToMany
	private List<User>  subscriptions; //подпиcки;
	
	@ManyToMany
	private List<User>  subscribers; // подписчики;
	
	@ManyToMany
	private List<Theme> topicSubscriptions; //подписки на темы;
	
	@OneToMany
	private List<Theme> listOfUserTopics; // список тем которые пользователь создал;
	
	public UserStatistics() {}
	
	public Integer getReputation() {
		return reputation;
	}
	
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	
	public Integer getActivity() {
		return activity;
	}
	
	public void setActivity(Integer activity) {
		this.activity = activity;
	}
	
	public List<User> getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(List<User> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public List<User> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}
	
	public List<Theme> getTopicSubscriptions() {
		return topicSubscriptions;
	}
	
	public void setTopicSubscriptions(List<Theme> topicSubscriptions) {
		this.topicSubscriptions = topicSubscriptions;
	}
	
	public List<Theme> getListOfUserTopics() {
		return listOfUserTopics;
	}
	
	public void setListOfUserTopics(List<Theme> listOfUserTopics) {
		this.listOfUserTopics = listOfUserTopics;
	}

}
