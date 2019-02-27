package de.jan.boot.client;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import de.jan.boot.model.Dish;

@SessionScope
//@FacesConverter("converter.DishConverter") - disallows @Autowire, that is why its commented out!
public class DishConverter implements Converter<Dish> {

	@Autowired
	DishBean dishBean;
	
	public DishConverter() {
		System.out.println("DishConverter constructor");
	}

	public Dish getAsObject(FacesContext arg0, UIComponent arg1, String dishName) {
		Dish dish = findDishByName(dishName);
		return dish;
	}
	
	private Dish findDishByName(String dishName) {

		if (dishName != null && dishBean == null) {
			return new Dish("none", "none", null, "");
		}
		
		for (Dish dish : dishBean.getDishes()) {
			if (dish != null && dishName != null && dishName.equals(dish.getName())) {
				return dish;
			}
		} 
		
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Dish value) {
		return value.getName();
	}
	
}
