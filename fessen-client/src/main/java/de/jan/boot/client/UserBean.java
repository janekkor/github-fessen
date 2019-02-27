package de.jan.boot.client;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import de.jan.boot.model.AppUser;
import de.jan.boot.service.DishRestCallerService;

@Component
@SessionScope
public class UserBean {

	private String id;
	private String password;
	private AppUser currentUser;
	private List<AppUser> users;
	
	@Autowired
	DishRestCallerService drService;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public AppUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AppUser currentUser) {
		this.currentUser = currentUser;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	private String getCurrentUserShortName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public AppUser retrieveCurrentUser() {
		this.currentUser = drService.findUserByShortName(getCurrentUserShortName());
		
		return this.currentUser;
	}
	
	public String getCurrentUserFirstName() {
		return retrieveCurrentUser().getFirstName();
	}
	
	public void onUserChange() {
		System.out.println("onUserChange in UserBean");
		// TODO Implementieren.
	}
}
