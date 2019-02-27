package de.jan.boot.client;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;

import de.jan.boot.model.AppUser;

@FacesConverter("converter.AppUserConverter")
public class AppUserConverter implements Converter<AppUser> {

	@Autowired
	UserBean userBean;
	
	public AppUserConverter() {
		System.out.println("AppUserConverter constructor");
	}

	public AppUser getAsObject(FacesContext arg0, UIComponent arg1, String userName) {
		AppUser appUser = findUserByName(userName);
		return appUser;
	}
	
	private AppUser findUserByName(String userName) {

		if (userName != null && userBean == null) {
			return new AppUser("none", "none", true, "NoFirstName", "NoLastName", null, "");
		}
		
		for (AppUser user : userBean.getUsers()) {
			if (user != null && userName != null && userName.equals(user.getFirstName())) {
				return user;
			}
		} 
		
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, AppUser value) {
		return value.getFirstName();
	}
	
}
