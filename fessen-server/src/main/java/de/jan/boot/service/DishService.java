package de.jan.boot.service;

import java.util.List;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;

public interface DishService {
	
	List<Dish> readAllDishes();
	
	List<AppUser> readAllUsers();
	
	List<Schedule> readAllSchedules();

	Schedule saveSchedule(Schedule newSchedule);
	
}
