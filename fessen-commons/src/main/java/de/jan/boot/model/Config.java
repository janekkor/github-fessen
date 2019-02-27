package de.jan.boot.model;

import java.util.Date;

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
@Table(name="CONFIG")
public class Config
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	//Parameter name
	@Column(name="KEY_NAME")
	private String name;
	
	//Parameter value
	@Column(name="KEY_VALUE")
	private String value;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	//Modification time
	@Column(name="MOD_TIME")
	private Date modTime;

	//Modification user
	@Column(name="MOD_USER")
	private String modUser;
	
	public Config() {
		//empty for hibernate
	}

	public Config(String name, String value, String description, Date modTime, String modUser) {
		super();
		this.name = name;
		this.value = value;
		this.description = description;
		this.modTime = modTime;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", name=" + name + ", value=" + value + ", description=" + description
				+ ", modTime=" + modTime + ", modUser=" + modUser + "]";
	}
	
}
