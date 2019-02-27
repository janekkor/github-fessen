package de.jan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.Dish;

public interface DishDao extends JpaRepository<Dish, Long> {
	
}
