package com.naehyung.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naehyung.dao.ChatRoomDao;
import com.naehyung.model.ChatRoom;
import com.naehyung.model.User;

@Service
@Transactional
public class ChatRoomService {

	@Autowired
	private ChatRoomDao chatRoomDao;
	
	public void checkRoom(List<User> userList) {
		
		
		
	}
	
	public List<ChatRoom> getAllChatRooms() {
		
		return chatRoomDao.findAll();
		
		
		
	}
	public List<ChatRoom> getChatRooomByUser(String username) {
		
		List<ChatRoom> chatRoomList = new ArrayList<>();
		
		System.out.println(username);
		
		List<ChatRoom> allChatRoomList = chatRoomDao.findAll();
		for(int i = 0; i < allChatRoomList.size(); i++) {
			System.out.println(allChatRoomList.size());
			for(String str :allChatRoomList.get(i).getUserList().stream().distinct().collect(Collectors.toList())) {
				
				 
				
				System.out.println(username);
				System.out.println(str);
				if(username.contains(str)) {
					System.out.println(allChatRoomList.get(i).getId());
					System.out.println(allChatRoomList.get(i).getRoomId());
					chatRoomList.add(allChatRoomList.get(i));
					
				}
			}
			
			
		}
		
		return chatRoomList;
		
		
	}
	
	public ChatRoom searchByRoomId(String roomId) {
		
		List<ChatRoom> tempChatRoomList = chatRoomDao.findAll();
		
		ChatRoom returnChatRoom = new ChatRoom();
		
		for(ChatRoom chatRoom : tempChatRoomList) {
			
			if(chatRoom.getRoomId().equals(roomId)) {
				
				returnChatRoom = chatRoom;
				
			}
			
		}
		
		return returnChatRoom;
		
	}
	
	public ChatRoom createChatRoom(List<User> userList) {
		
		List<String> usernameList = new ArrayList<>();
		
		for(int i = 0; i< userList.size(); i++) {
			
			usernameList.add(userList.get(i).getEmail());
			
		}
		
		ChatRoom tempChatRoom = new ChatRoom();
		
		String roomId = UUID.randomUUID().toString();
		
		tempChatRoom.setRoomId(roomId);
		
		tempChatRoom.setUserList(usernameList);
				
		chatRoomDao.save(tempChatRoom);
		
		chatRoomDao.flush();
		
		return tempChatRoom;
		
		
	}
	
	public void update(ChatRoom chatRoom) {
		
		chatRoomDao.save(chatRoom);
		chatRoomDao.flush();
		
		
		
	}
	
	
	
	
}
