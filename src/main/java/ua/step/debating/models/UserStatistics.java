package ua.step.debating.models;

import java.util.List;

public class UserStatistics {
	private Integer debatCount;
	private Integer discussionCount;
	private List<Lobby> listOfLobbyDebat;
	private List<Lobby> listOfLobbyDiscussion;
	private List<Lobby> listOfMyCreatedDebat; //список дискуссий которые я создал;
	private List<Lobby> listOfMyCreatedDiscussion; //список дебатов которые я создал;
	private List<Message> message;
	public List<Message> getMessage() {
		return message;
	}
	public void setMessage(List<Message> message) {
		this.message = message;
	}
	public List<Message> getListOfSpendedPointsForMessage() {
		return listOfSpendedPointsForMessage;
	}
	public void setListOfSpendedPointsForMessage(List<Message> listOfSpendedPointsForMessage) {
		this.listOfSpendedPointsForMessage = listOfSpendedPointsForMessage;
	}
	private Integer reputation ;
	private Integer activity;
	private List<User>  subscriptions; //подпиcки;
	private List<User>  subscribers; // подписчики;
	private List<Theme> topicSubscriptions; //подписки на темы;
	private List<Theme> listOfUserTopics; // список тем которые пользователь создал;
	private List<User> listOfSpendedPointsForUsers;  // список на что я потратил баллов
	private List<Message> listOfSpendedPointsForMessage;  // список на что я потратил баллы
	
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
	public List<User> getListOfSpendedPointsForUsers() {
		return listOfSpendedPointsForUsers;
	}
	public void setListOfSpendedPointsForUsers(List<User> listOfSpendedPointsForUsers) {
		this.listOfSpendedPointsForUsers = listOfSpendedPointsForUsers;
	}

}
