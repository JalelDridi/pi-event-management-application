package tn.esprit.notificationmodule.entities;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data // Lombok annotation to get rid of most boilerplate code like : getters, setters, equals, toString ....
@Document(collection = "messages")
public class Message implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "messages_sequence";

    @Id
    private Long messageId;

    private String subject;
    private String content;
    private LocalDateTime sentDate;

}
