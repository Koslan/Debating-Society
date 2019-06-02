package ua.step.debating.models;

import java.util.List;

public class UserStatistics {
	
	private Integer reputation;
	
	private Integer activity;
	
	private List<User>  subscriptions; //подпиcки;
	
	private List<User>  subscribers; // подписчики;
	
	private List<Theme> topicSubscriptions; //подписки на темы;
	
	private List<Theme> listOfUserTopics; // список тем которые пользователь создал;
	
	UserStatistics() {}
	
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
