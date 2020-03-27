package se228.richard.ebookstore.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import se228.richard.ebookstore.entity.InputMessage;
import se228.richard.ebookstore.entity.OutputMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatRoomController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(final InputMessage inputMessage) throws Exception {
        final String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(inputMessage.getSender(), time, inputMessage.getContent());
    }

}
