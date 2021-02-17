package com.naehyung.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.naehyung.web.model.EchoModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SocketService {
	
	@Autowired
    private SimpMessagingTemplate simpTemplate;

    public void echoMessage(String message) {
        log.debug("Start convertAndSend ${new Date()}");
        simpTemplate.convertAndSend("/topic/greetings", new EchoModel(message));
        log.debug("End convertAndSend ${new Date()}");
    }
    
   

}
