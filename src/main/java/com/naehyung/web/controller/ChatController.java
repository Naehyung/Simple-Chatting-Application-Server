package com.naehyung.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naehyung.web.model.DataItem;
import com.naehyung.web.model.EchoModel;
import com.naehyung.web.service.SocketService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChatController {
	
	@Autowired
    private SocketService socketService;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/hello-msg-mapping")
    public EchoModel echoMessageMapping(String message) {
        log.debug("React to hello-msg-mapping");
        System.out.println(message);
        return new EchoModel(message.trim() + "hi");
    }
    
   
    
    @MessageMapping("/chatting")
    

    @RequestMapping(value = "/hello-convert-and-send", method = RequestMethod.POST)
    public void echoConvertAndSend(@RequestParam("msg") String message) {
        socketService.echoMessage(message);
    }

}
