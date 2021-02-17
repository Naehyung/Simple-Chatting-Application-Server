package com.naehyung.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naehyung.model.User;
import com.naehyung.web.model.DataItem;

public interface DataItemDao extends JpaRepository<DataItem, Integer>{
	
	

}
