package de.jan.boot.model;

import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Bean class
 *
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private AppUser user;
	
	@OneToOne
	@JoinColumn(name="DISH_ID")
	private Dish dish;
	
	@Column(name="DAY")
	private Date day;
	
	//Modification time
	@Column(name="MOD_TIME")
	private Date modTime;

	//Modification user
	@Column(name="MOD_USER")
	private String modUser = null;

	public Schedule() {
		//empty for hibernate
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	
	
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", user=" + user + ", dish=" + dish + ", day=" + day + ", modTime=" + modTime
				+ ", modUser=" + modUser + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((dish == null) ? 0 : dish.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Schedule other = (Schedule) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (dish == null) {
			if (other.dish != null)
				return false;
		} else if (!dish.equals(other.dish))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public boolean equalUserAndDay(Schedule schedule) {
		
		if (this == schedule)
			return true;
		if (schedule == null)
			return false;
		if (day == null) {
			if (schedule.day != null)
				return false;
//		} else if (!day.equals(schedule.day))
		} else if (!DateUtils.isSameDay(this.day, schedule.day))
			return false;
		if (user == null) {
			if (schedule.user != null)
				return false;
		} else if (!user.equals(schedule.user))
			return false;
		return true;
	}

}
