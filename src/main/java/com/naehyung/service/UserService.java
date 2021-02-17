package com.naehyung.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naehyung.dao.ChatRoomDao;
import com.naehyung.dao.UserDao;
import com.naehyung.model.ChatRoom;
import com.naehyung.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public List<User> setUserEntity(List<User> userList) {
		
		List<User> userListTemp = dao.findAll();
		
		for(int i = 0; i < userListTemp.size(); i++) {
			
			for(int j =0; j< userList.size(); j++) {
				
				if(userListTemp.get(i).getEmail().equals(userList.get(j).getEmail())) {
					
					userList.get(j).setId(userListTemp.get(i).getId());
					userList.get(j).setPassword(userListTemp.get(i).getPassword());
					
				}
				
				
			}
			
			
		}
		
		return userList;
	}
	
	
	public void saveUser(User user) {
		
		dao.save(user);
	}
	
	public List<User> getUsers() {
		
		List<User> userListTemp= new ArrayList<User>();
		
		for(int i = 0; i < dao.findAll().size(); i++) {
			
			User userTemp = new User();
			
			userTemp.setId(dao.findAll().get(i).getId());
			userTemp.setEmail(dao.findAll().get(i).getEmail());
			userTemp.setPassword(dao.findAll().get(i).getPassword());
			
			
			userListTemp.add(userTemp);
			
		}
		
		
		return userListTemp;
		
	}
	
	public void deleteAll() {
		
		dao.deleteAll();
	}
	
	
	public User loginCheck(User user) {
		
		User returnUser = new User();
		
		List<User> userList = dao.findAll();
		
		for(int i = 0; i < userList.size(); i++) {
			
			if(userList.get(i).getEmail().equals(user.getEmail())
					&& userList.get(i).getPassword().equals(user.getPassword())) {
								
				returnUser = user;
				
			}
				
		}
		
		return returnUser; 
		
	}
	

}
