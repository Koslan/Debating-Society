package ua.step.debating.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Configurations")
public class Configuration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	private TalkType talkType; // дебаты/или дискуссия
	private Integer generalTime; // время дискуссии/дебатов
	private Integer timeoutTime;
	private Integer timeoutFine; // штраф за истечение таймаута
	private Boolean isCensorship = false;
	private Boolean isSpectatorChatVisible = false;
	@OneToMany
	private List<User> orderOfDescants;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGeneralTime() {
		return generalTime;
	}

	public void setGeneralTime(Integer generalTime) {
		this.generalTime = generalTime;
	}

	public Integer getTimeoutTime() {
		return timeoutTime;
	}

	public void setTimeoutTime(Integer timeoutTime) {
		this.timeoutTime = timeoutTime;
	}

	public Integer getTimeoutFine() {
		return timeoutFine;
	}

	public void setTimeoutFine(Integer timeoutFine) {
		this.timeoutFine = timeoutFine;
	}

	public Boolean getIsCensorship() {
		return isCensorship;
	}

	public void setIsCensorship(Boolean isCensorship) {
		this.isCensorship = isCensorship;
	}

	public Boolean getIsSpectatorChatVisible() {
		return isSpectatorChatVisible;
	}

	public void setIsSpectatorChatVisible(Boolean isSpectatorChatVisible) {
		this.isSpectatorChatVisible = isSpectatorChatVisible;
	}

	public List<User> getOrderOfDescants() {
		return orderOfDescants;
	}

	public void setOrderOfDescants(List<User> orderOfDescants) {
		this.orderOfDescants = orderOfDescants;
	}

	public void setTalkType(TalkType debate) {
		talkType = debate;

	}

	public TalkType getTalkType() {
		return talkType;
	}
}
