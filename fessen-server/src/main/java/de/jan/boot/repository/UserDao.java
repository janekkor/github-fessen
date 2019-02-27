package de.jan.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.jan.boot.model.AppUser;

public interface UserDao extends JpaRepository<AppUser, Long> {
	
}
