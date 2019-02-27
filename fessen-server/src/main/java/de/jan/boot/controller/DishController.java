package de.jan.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;
import de.jan.boot.service.DishService;

@RestController
public class DishController {

	@Autowired
	private DishService dishService;
	
	@RequestMapping("/")
	public Dish home( ) {
		List<Dish> dishes = dishService.readAllDishes();
		return dishes.get(new Double(Math.random() * dishes.size()).intValue());
	}
	
	@RequestMapping("/dishes")
	public List<Dish> retrieveDishes() {
		return dishService.readAllDishes();
	}
	
	@RequestMapping("/users")
	public List<AppUser> retrieveUsers() {
		return dishService.readAllUsers();
	}

	@RequestMapping("/schedules")
	public List<Schedule> retrieveSchedules() {
		return dishService.readAllSchedules();
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	ResponseEntity<?> newSchedule(@RequestBody Schedule schedule) {
		
		System.out.println("************* POST schedule auf der Serverseite. Received schedule with id: " + schedule.getId() + " *************************");
		Schedule savedSchedule = dishService.saveSchedule(schedule);
		return new ResponseEntity<Schedule>(savedSchedule, HttpStatus.CREATED);
	}
	
	@RequestMapping("/mama")
	public String mamasMethod( ) {
		return "Hallo Mama, lass uns Freitagsessen Bestellapplikation zusammenbasteln!";
	}
	
	@RequestMapping("/papa")
	public String papasMethod( ) {
		return "Hallo Papa, schön, dass es jetzt von GUI bis DB auf dem Raspberry Pi läuft!";
	}
	
	@RequestMapping("/dominik")
	public String dominiksMethod( ) {
		return "Hallo Dominik, freust du dich schon auf Hann. Münden?";
	}
	
	@RequestMapping("/ewelina")
	public String ewelinasMethod( ) {
		return "Hallo Ewelina, freust du dich schon auf die Herbstferien?";
	}
	
}
