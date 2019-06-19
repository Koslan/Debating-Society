package ua.step.debating.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Votes")
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	private Date estimateDate;
	
	@ManyToOne
	private User estimater;
	
	@ManyToOne
	private User chosen;
	
	public Vote() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public User getEstimater() {
		return estimater;
	}

	public void setEstimater(User estimater) {
		this.estimater = estimater;
	}

	public User getFavorite() {
		return chosen;
	}

	public void setFavorite(User favorite) {
		this.chosen = favorite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estimateDate == null) ? 0 : estimateDate.hashCode());
		result = prime * result + ((estimater == null) ? 0 : estimater.hashCode());
		result = prime * result + ((chosen == null) ? 0 : chosen.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (estimateDate == null) {
			if (other.estimateDate != null)
				return false;
		} else if (!estimateDate.equals(other.estimateDate))
			return false;
		if (estimater == null) {
			if (other.estimater != null)
				return false;
		} else if (!estimater.equals(other.estimater))
			return false;
		if (chosen == null) {
			if (other.chosen != null)
				return false;
		} else if (!chosen.equals(other.chosen))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", estimateDate=" + estimateDate + ", estimater=" + estimater + ", chosen="
				+ chosen + "]";
	}
	
}



