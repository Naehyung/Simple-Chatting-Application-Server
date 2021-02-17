package com.naehyung.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naehyung.model.ChatRoom;

public interface ChatRoomDao extends JpaRepository<ChatRoom, Integer>{
	
	

}
