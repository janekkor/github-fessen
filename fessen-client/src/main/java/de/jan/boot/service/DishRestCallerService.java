package de.jan.boot.service;

import java.util.Date;
import java.util.List;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;

public interface DishRestCallerService {

	Dish retrieveRandomDish();

	List<Dish> retrieveAllDishes();
	
	List<AppUser> retrieveAllUsers();

	AppUser findUserByShortName(String userShortName);

	List<Schedule> retrieveAllSchedules();

	Schedule saveDishSelection(Schedule scheduleIn);

}