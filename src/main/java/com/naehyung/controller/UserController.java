package com.naehyung.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naehyung.dao.UserDao;
import com.naehyung.model.ChatRoom;
import com.naehyung.model.User;
import com.naehyung.service.ChatRoomService;
import com.naehyung.service.DataItemService;
import com.naehyung.service.UserService;
import com.naehyung.web.controller.ChatController;
import com.naehyung.web.model.DataItem;
import com.naehyung.web.model.EchoModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	DataItemService dataItemService;
	
	@Autowired
    private SimpMessagingTemplate template;
	
	 @MessageMapping("/chatting-room/{roomId}")
	    public void dataItemMessageMapping(@DestinationVariable String roomId, Message<DataItem> message) {
	    	log.debug("React to hello-msg-mapping");
	    	DataItem m = message.getPayload();
	    	System.out.println(roomId);
	    	System.out.println(m.getContent()+m.getName());
	    	
	    	DataItem tempData = new DataItem(m.getContent(), m.getName(), m.getViewType());
	    	
	    	ChatRoom chatRoom = chatRoomService.searchByRoomId(roomId);
	    	
	    	tempData.setChatRoom(chatRoom);
	    	
	    	chatRoom.getDataItemList().add(tempData);
	    	
	    	//dataItemService.save(tempData);
	    	
	    	chatRoomService.update(chatRoom);
	    	
	    	
	    	
	    	this.template.convertAndSend("/topic/chatting/" + roomId, new DataItem(m.getContent(), m.getName(), m.getViewType()));
	    	//return new DataItem(m.getContent(), m.getName(), m.getViewType());
	    }
	
//	@MessageMapping("/chatting-room/{roomId}")
//    @SendTo("/topic/chatting/{roomId}")
//    public DataItem dataItemMessageMapping(@DestinationVariable String roomId, Message<DataItem> message) {
//    	log.debug("React to hello-msg-mapping");
//    	DataItem m = message.getPayload();
//    	System.out.println(roomId);
//    	System.out.println(m.getContent());
//    	return new DataItem(m.getContent(), m.getName(), m.getViewType());
//    }
	
	@PostMapping("/chatting-room-users")
    public ChatRoom echoMessageMapping(@RequestBody List<User> message) {
		
		//확인먼저
		System.out.println(message.get(0).getEmail()+message.get(1).getEmail());
		
		List<String> userListTemp = new ArrayList<>();
		
		for(int i = 0; i < message.size(); i++) {
			
			userListTemp.add(message.get(i).getEmail());
			
		}
		
		List<ChatRoom> chatRoomList = chatRoomService.getAllChatRooms();
		
		for(int i = 0; i < chatRoomList.size(); i++) {
			
			if(cmp(chatRoomList.get(i).getUserList(),userListTemp)) {
				
				return chatRoomList.get(i);
				
			}
			
		}
		
		ChatRoom temp = chatRoomService.createChatRoom(message);		
		
        return temp;
    }
	
	private static boolean cmp( List<?> l1, List<?> l2 ) {
	    // make a copy of the list so the original list is not changed, and remove() is supported
	    ArrayList<?> cp = new ArrayList<>( l1 );
	    for ( Object o : l2 ) {
	        if ( !cp.remove( o ) ) {
	            return false;
	        }
	    }
	    return cp.isEmpty();
	}
	
	@PostMapping("/CheckRoom")
	public void checkRoom(@RequestBody ChatRoom chatRoom) {
		
		List<ChatRoom> chatRoomList = chatRoomService.getAllChatRooms();
		
		for(int i = 0; i < chatRoomList.size(); i++) {
			
			
			
		}
		
	}
	
	@PostMapping("/addUsers")
	public User addUsers(@RequestBody User user) {
		
		userService.saveUser(user);
		return user;
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		
		return userService.getUsers();
		
	}
	
	@PostMapping("/getChatRooms")
	public List<ChatRoom> getChatRooms(@RequestBody String username) {
		
		System.out.println(username+"getChatRooms");
		
		return chatRoomService.getChatRooomByUser(username);	
		
		
		
	}
	
	@PostMapping("/loginCheck")
	public @ResponseBody User loginCheck(@RequestBody User user) {
		
		
		return userService.loginCheck(user);
		
	}
	
	
	
	
	
	
}
