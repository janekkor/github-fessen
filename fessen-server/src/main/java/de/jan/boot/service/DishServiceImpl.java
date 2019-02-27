package de.jan.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;
import de.jan.boot.repository.DishDao;
import de.jan.boot.repository.ScheduleDao;
import de.jan.boot.repository.UserDao;

@Transactional
@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<Dish> readAllDishes() {
		return dishDao.findAll();
	}
	
	@Override
	public List<AppUser> readAllUsers() {
		return userDao.findAll();
	}
	
	@Override
	public List<Schedule> readAllSchedules() {
		return scheduleDao.findAll();
	}
	
	@Override
	public Schedule saveSchedule(Schedule newSchedule) {
		Schedule dbSchedule = findScheduleByUserAndDay(newSchedule);
		
		if (dbSchedule != null) {
			dbSchedule.setDish(newSchedule.getDish());
			return scheduleDao.save(dbSchedule);
		}
		
		return scheduleDao.save(newSchedule);
	}

	private Schedule findScheduleByUserAndDay(Schedule newSchedule) {
		//TODO: Change the logic to search with SQL instead of searching with Java
		List<Schedule> allSchedules = readAllSchedules();
		for (Schedule schedule : allSchedules) {
			if (schedule.equalUserAndDay(newSchedule)) {
				return schedule;
			}
		}
		
		
		return null;
	}
}
