package org.example.notificationmodule.entities;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data // Lombok annotation to get rid of most boilerplate code like : getters, setters, equals, toString ....
@Document(collection = "messages")
public class Message implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "messages_sequence";

    @Id
    private Long messageId;

    private String content;
    private LocalDateTime sentDate;

}
