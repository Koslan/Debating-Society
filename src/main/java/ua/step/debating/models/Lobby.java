package ua.step.debating.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Lobbies")
public class Lobby {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String name;
	@OneToOne
	private Theme theme;
	
	private LocalDateTime createDate;

	//private Date startDate;
	//время не поиск аппонента
	private LocalDateTime dateOfDebate;

	private LocalDateTime timeToStartDebate;
	private LocalDateTime timeToFinishDebate;

	private Boolean timeToAnswer1;
	private Boolean timeToAnswer2;

	private Boolean debateStart;
	private Boolean debateGoing;
	private Boolean debateFinish;

	private Boolean isOpponentsfound;
	
	@ManyToMany
	private List<User> firstSide = new ArrayList<User>();
	
	@ManyToMany
	private List<User> secondSide = new ArrayList<User>();
	
	@ManyToMany
	private List<User> spectators = new ArrayList<User>();
	@OneToMany
	private List<Message> firstSideMessages = new ArrayList<Message>();
	@OneToMany
	private List<Message> secondSideMessages = new ArrayList<Message>();
	
	@OneToOne
	private Configuration config;
	
	private Boolean active;
	
	@ManyToMany
	private List<User> winners = new ArrayList<User>();

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

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public List<User> getFirstSide() {
		return firstSide;
	}

	public void setFirstSide(List<User> firstSide) {
		this.firstSide = firstSide;
	}

	public List<User> getSecondSide() {
		return secondSide;
	}

	public void setSecondSide(List<User> secondSide) {
		this.secondSide = secondSide;
	}

	public List<User> getSpectators() {
		return spectators;
	}

	public void setSpectators(List<User> spectators) {
		this.spectators = spectators;
	}

	public List<Message> getFirstSideMessages() {
		return firstSideMessages;
	}

	public void setFirstSideMessages(List<Message> firstSideMessages) {
		this.firstSideMessages = firstSideMessages;
	}

	public List<Message> getSecondSideMessages() {
		return secondSideMessages;
	}

	public void setSecondSideMessages(List<Message> secondSideMessages) {
		this.secondSideMessages = secondSideMessages;
	}

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<User> getWinners() {
		return winners;
	}

	public void setWinners(List<User> winners) {
		this.winners = winners;
	}
	
public LocalDateTime getDateOfDebate() {
	return dateOfDebate;
}

public void setDateOfDebate(LocalDateTime dateOfDebate) {
	this.dateOfDebate = dateOfDebate;
}

public Boolean getTimeToAnswer1() {
	return timeToAnswer1;
}

public void setTimeToAnswer1(Boolean timeToAnswer1) {
	this.timeToAnswer1 = timeToAnswer1;
}

public Boolean getTimeToAnswer2() {
	return timeToAnswer2;
}

public void setTimeToAnswer2(Boolean timeToAnswer2) {
	this.timeToAnswer2 = timeToAnswer2;
}

public Boolean getDebateStart() {
	return debateStart;
}

public void setDebateStart(Boolean debateStart) {
	this.debateStart = debateStart;
}

public Boolean getDebateGoing() {
	return debateGoing;
}

public void setDebateGoing(Boolean debateGoing) {
	this.debateGoing = debateGoing;
}

public Boolean getDebateFinish() {
	return debateFinish;
}

public void setDebateFinish(Boolean debateFinish) {
	this.debateFinish = debateFinish;
}

public Boolean getIsOpponentsfound() {
	return isOpponentsfound;
}

public void setIsOpponentsfound(Boolean isOpponentsfound) {
	this.isOpponentsfound = isOpponentsfound;
}

public LocalDateTime getTimeToFinishDebate() {
	return timeToFinishDebate;
}

public void setTimeToFinishDebate(LocalDateTime timeToFinishDebate) {
	this.timeToFinishDebate = timeToFinishDebate;
}

public LocalDateTime getTimeToStartDebate() {
	return timeToStartDebate;
}

public void setTimeToStartDebate(LocalDateTime timeToStartDebate) {
	this.timeToStartDebate = timeToStartDebate;
}

}


