package de.jan.boot.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import de.jan.boot.model.AppUser;
import de.jan.boot.model.Dish;
import de.jan.boot.model.Schedule;
import de.jan.boot.service.DishRestCallerService;

@Component
@SessionScope
public class DishBean {

	@Autowired
	DishRestCallerService drService;

	@Autowired
	UserBean userBean;

	private List<Dish> dbDishes;
	private List<String> guiDishes;
	private List<Schedule> allSchedules;
	private List<Schedule> mySchedules;
	private List<Schedule> futureSchedules;

	private String selectedDish;
	private String selectedFessenDay;

	List<String> nextFessenDays = null;

	@PostConstruct
	public void init() {
		retrieveAllDishes();
		getNextFourFirdayDates();
		retrieveAllSchedules();
	}

	private void retrieveAllSchedules() {
		this.allSchedules = drService.retrieveAllSchedules();
		// TODO: sort allSchedules by date
		initializeSchedules();

	}

	private void initializeSchedules() {
		this.mySchedules = new ArrayList<>();
		this.futureSchedules = new ArrayList<>();
		AppUser currentUser = userBean.getCurrentUser();

		for (Schedule schedule : this.allSchedules) {
			if (schedule.getDay().after(new Date())) {
				this.futureSchedules.add(schedule);
				if (schedule.getUser().equals(currentUser)) {
					this.mySchedules.add(schedule);
				}
			}
		}

	}

	private void retrieveAllDishes() {
		this.dbDishes = drService.retrieveAllDishes();
		initializeGuiDishes(this.dbDishes);
	}

	private void initializeGuiDishes(List<Dish> dishes) {
		this.guiDishes = new ArrayList<>();
		for (Dish dish : dishes) {
			guiDishes.add(dish.getName());
		}
	}

	public List<Dish> getDishes() {
		retrieveAllDishes();
		return dbDishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dbDishes = dishes;
	}

	public List<Schedule> getAllSchedules() {
		retrieveAllSchedules();
		return allSchedules;
	}

	public void setAllSchedules(List<Schedule> schedules) {
		this.allSchedules = schedules;
	}

	public String getSelectedDish() {
		return selectedDish;
	}

	public void setSelectedDish(String selectedDish) {
		this.selectedDish = selectedDish;
	}

	public List<String> getNextFessenDays() {
		return nextFessenDays;
	}

	public void setNextFessenDays(List<String> dates) {
		this.nextFessenDays = dates;
	}

	public List<String> getNextFourFirdayDates() {

		if (this.nextFessenDays == null) {
			this.nextFessenDays = new ArrayList<String>();
		} else {
			return this.nextFessenDays;
		}

		Date fridayDate = getNextFriday();
		for (int i = 0; i < 4; i++) {
			this.nextFessenDays.add(formatDate(fridayDate));
			fridayDate = DateUtils.addWeeks(fridayDate, 1);
		}
		return this.nextFessenDays;
	}

	public String retrieveMyNextDate(String userId) {
		// Muss noch basierend auf START_DATE in CONFIG-Tabelle implementiert werden.
		return formatDate(getNextFriday());
	}

	private String formatDate(Date aDate) {
		// needs real implementation
		return new SimpleDateFormat("dd.MM.yyyy").format(aDate);
	}

	private Date getNextFriday() {
		Calendar date = Calendar.getInstance();
		int weekday = date.get(Calendar.DAY_OF_WEEK);
		if (weekday != Calendar.FRIDAY) {
			// calculate how much to add
			// the 6 is the difference between Saturday and Friday
			int days = (Calendar.SATURDAY - weekday + 6) % 7;
			date.add(Calendar.DAY_OF_YEAR, days);
		}
		// now is the date you want
		return date.getTime();
	}

	public void saveDishSelection() {
		AppUser currentUser = userBean.getCurrentUser();

		Schedule scheduleIn = new Schedule();
		// scheduleIn.setDay(DateUtils.truncate(getNextFriday(), Calendar.DATE));
		scheduleIn.setDay(parseDate(selectedFessenDay));
		scheduleIn.setDish(findDishByName(this.selectedDish));
		scheduleIn.setUser(currentUser);
		scheduleIn.setModTime(new Date());
		scheduleIn.setModUser(currentUser.getShortName());

		drService.saveDishSelection(scheduleIn);

		retrieveAllSchedules();
	}

	private Date parseDate(String aDate) {
		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(aDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException(aDate + " is not a valid date to be parsed!!!");
		}
	}

	private Dish findDishByName(String dishName) {
		if (dishName == null || dishName.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Dish argument to the method DishBean.findDishByName(dish) is null or empty!!!");
		}

		for (Dish aDish : this.dbDishes) {
			if (dishName.equals(aDish.getName())) {
				return aDish;
			}
		}

		throw new IllegalArgumentException("The dish in DishBean.findDishByName(dish) could not be found!!!");
	}

	public void onDishChange() {
		// TODO Implementieren: Gericht einem Benutzer und dem Datum zuweisen, wenn
		// entsprechende Tabellen in DB da sind.
	}

	public List<String> getGuiDishes() {
		return guiDishes;
	}

	public void setGuiDishes(List<String> guiDishes) {
		this.guiDishes = guiDishes;
	}

	public String getSelectedFessenDay() {
		return selectedFessenDay;
	}

	public void setSelectedFessenDay(String selectedFessenDay) {
		this.selectedFessenDay = selectedFessenDay;
	}

	public List<Schedule> getMySchedules() {
		return mySchedules;
	}

	public void setMySchedules(List<Schedule> mySchedules) {
		this.mySchedules = mySchedules;
	}

	public List<Schedule> getFutureSchedules() {
		return futureSchedules;
	}

	public void setFutureSchedules(List<Schedule> futureSchedules) {
		this.futureSchedules = futureSchedules;
	}
}
