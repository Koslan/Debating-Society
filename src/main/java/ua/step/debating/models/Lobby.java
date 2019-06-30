package ua.step.debating.models;

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
	
	private Date createDate;

	private Date startDate;
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}



