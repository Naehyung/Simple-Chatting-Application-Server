package com.naehyung.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.naehyung.model.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	

}
