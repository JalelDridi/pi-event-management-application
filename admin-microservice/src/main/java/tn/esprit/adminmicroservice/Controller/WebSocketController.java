package tn.esprit.adminmicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import tn.esprit.adminmicroservice.Dto.ChatMessage;

import java.io.IOException;

@Controller
public class WebSocketController {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}")
    public void chat(@DestinationVariable String roomId, ChatMessage message) throws IOException {
        String sender = message.getUser();

        if (message.getFileData() != null && message.getFileName() != null && message.getFileType() != null) {
            byte[] fileContent = message.getFileData();

            message.setMessage("Received a file from " + sender + ": " + message.getFileName());

            System.out.println("Received file from " + sender + ": " + message.getFileName() + ", type: " + message.getFileType() + ", length: " + fileContent.length + " bytes");

            messagingTemplate.convertAndSend("/topic/" + roomId, message);
        } else {
            System.out.println("Received message from " + sender + ": " + message.getMessage());

            messagingTemplate.convertAndSend("/topic/" + roomId, message);
        }
    }
}
