package de.jan.boot.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;

/**
 * My first boot REST client
 *
 */
@Component
public class DishRestCallerServiceImpl implements DishRestCallerService 
{
        private static final Logger log = LoggerFactory.getLogger(DishRestCallerServiceImpl.class);

        @Value("${restServer.dishURLWithPort}")
        private String restServerURLWithPort;
        
		@Override
		public Dish retrieveRandomDish() {
			RestTemplate restTemplate = new RestTemplate();
            //Dish responseDish = restTemplate.getForObject("http://korolko.ddns.net:8081/", Dish.class);
			Dish responseDish = restTemplate.getForObject(restServerURLWithPort, Dish.class);
            log.info("\n********************************************************");
            log.info("\n" + responseDish.toString());
            log.info("\n********************************************************");
            return responseDish;
		}
        
        @Override
		public List<Dish> retrieveAllDishes() {
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<List<Dish>> dishResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/dishes",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Dish>>() {
        	            });
        	List<Dish> dishes = dishResponse.getBody();
        	log.info("\n" + dishes);
        	return dishes;
        }
        
        @Override
		public List<AppUser> retrieveAllUsers() {
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<List<AppUser>> userResponse =
        	        restTemplate.exchange(restServerURLWithPort + "/users",
        	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<AppUser>>() {
        	            });
        	List<AppUser> users = userResponse.getBody();
        	log.info("\n" + users);
        	return users;
        }

        @Override
        public List<Schedule> retrieveAllSchedules() {
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<List<Schedule>> scheduleResponse =
        			restTemplate.exchange(restServerURLWithPort + "/schedules",
        					HttpMethod.GET, null, new ParameterizedTypeReference<List<Schedule>>() {
        			});
        	List<Schedule> schedules = scheduleResponse.getBody();
        	log.info("\n" + schedules);
        	return schedules;
        }
        
        @Override
        public AppUser findUserByShortName(String userShortName) {
        	
        	//TODO: Cache the users instead of calling retrieveAllUsers() each time
        	List<AppUser> allUsers = retrieveAllUsers();
        	
        	for (AppUser appUser : allUsers) {
				if(userShortName.equalsIgnoreCase(appUser.getShortName())) {
					return appUser;
				}
			}
        	
        	return null;
        }

		@Override
		public Schedule saveDishSelection(Schedule scheduleIn) {
			// TODO Auto-generated method stub
			System.out.println("********DishRestCallerServiceImpl.saveDishSelection(Long dishId, Date scheduleDate) called!********");
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Schedule> scheduleResponse = restTemplate.postForEntity(restServerURLWithPort + "/schedule", scheduleIn, Schedule.class);

			Schedule scheduleOut = scheduleResponse.getBody();
			if (scheduleOut != null) {
				System.out.println("******** Schedule returned with id: " + scheduleOut.getId() + " *************");
			} else {
				System.out.println("******** No schedule returned from the REST server! ***********");
			}
			
			return scheduleOut;
		}
}
