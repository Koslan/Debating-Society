package ua.step.debating.models;

import java.util.List;

import org.springframework.ui.context.Theme;

public class UserStatistics {
	public Integer debatCount;
	public Integer discussionCount;
	public List<Lobby> listOfLobbyDebat;
	public List<Lobby> listOfLobbyDiscussion;
	public List<Lobby> listOfMyCreatedDebat; //список дискуссий которые я создал;
	public List<Lobby> listOfMyCreatedDiscussion; //список дебатов которые я создал;
	//public List<Message> messages;
	public Integer reputation ;
	public Integer activity;
	public List<User>  subscriptions; //подпиcки;
	public List<User>  subscribers; // подписчики;
	public List<Theme> topicSubscriptions; //подписки на темы;
	public List<Theme> listOfUserTopics; // список тем которые пользователь создал;
	public List<User> listOfSpendedPointsForUsers;  // список на что я потратил баллов
	//public List<Messages> listOfSpendedPointsForMessage;  // список на что я потратил баллы

}
