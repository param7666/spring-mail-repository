package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.model.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

	@Query("from User where email=?1 and password=?2")
	public User login(String email, String password);
}
