package de.jan.boot.bean;

import java.util.Date;

/**
 * AppUser Bean class
 *
 */
public class AppUserTO 
{
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	//Modification time
	private Date modTime;

	//Modification user
	private String modUser;
	
	public AppUserTO() {
		//empty
	}

	protected Date getTimeNow() {
		return new Date();
	}
	
	public AppUserTO(String firstName, String lastName, String modUser) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.modTime = getTimeNow();
		this.modUser = modUser;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return "AppUserTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", modTime=" + modTime
				+ ", modUser=" + modUser + "]";
	}
}
