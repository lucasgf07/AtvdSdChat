package br.edu.ifpb.AtdvSdChat.controller;

import br.edu.ifpb.AtdvSdChat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ChatController {

    List<String> users = new ArrayList<>();

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage,
                                SimpMessageHeaderAccessor headerAccessor) throws Exception {
        String sender = chatMessage.getSender();
        headerAccessor.getSessionAttributes().put("username", sender);
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        if(chatMessage.getContent() == "null"){
            return null;
        }else{
            return  chatMessage;
        }
    }

}
