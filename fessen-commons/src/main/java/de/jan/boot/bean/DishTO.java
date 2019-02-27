package de.jan.boot.bean;

import java.util.Date;

/**
 * Dish Bean class
 *
 */
public class DishTO 
{
	private Long id;
	
	private String name;
	
	private String description;
	
	//Modification time
	private Date modTime;

	//Modification user
	private AppUserTO modUser;
	
	public DishTO() {
		//empty
	}

	protected Date getTimeNow() {
		return new Date();
	}
	
	public DishTO(String name, String description, AppUserTO modUser) {
		super();
		this.name = name;
		this.description = description;
		this.modTime = getTimeNow();
		this.modUser = modUser;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public AppUserTO getModUser() {
		return modUser;
	}

	public void setModUser(AppUserTO modUser) {
		this.modUser = modUser;
	}

	@Override
	public String toString() {
		return "DishTO [id=" + id + ", name=" + name + ", description=" + description + ", modTime=" + modTime
				+ ", modUser=" + modUser + "]";
	}
}
