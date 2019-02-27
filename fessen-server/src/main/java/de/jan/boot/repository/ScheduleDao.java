package de.jan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {
	
}
