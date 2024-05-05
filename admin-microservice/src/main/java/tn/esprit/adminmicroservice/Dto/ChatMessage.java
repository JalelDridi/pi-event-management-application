package tn.esprit.adminmicroservice.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {

    private String message;
    private String user;
    private byte[] fileData;
    private String fileName;
    private String fileType;
    private boolean isVoiceMessage;
    private boolean isFileMessage;




}
