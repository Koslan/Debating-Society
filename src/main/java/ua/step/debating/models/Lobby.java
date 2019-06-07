package ua.step.debating.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Lobby")
public class Lobby {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private String name;
	// private Theme theme;
	private Date createDate;
	private String position1;
	private String position2;
	
	@ManyToMany
	private List<User> firstSide;
	
	@ManyToMany
	private List<User> secondSide;
	
	@ManyToMany
	private List<User> spectators;
	//private List<Message> firstSideMessages;
	//private List<Message> secondSideMessages;
	private String ImageURL;
	
	@OneToOne
	private Configuration config;
	private Boolean active;
	
	@ManyToMany
	private List<User> winners;
}



