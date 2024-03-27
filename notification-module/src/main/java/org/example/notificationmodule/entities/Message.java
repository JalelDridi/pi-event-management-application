package org.example.notificationmodule.entities;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data // Lombok annotation to get rid of most boilerplate code like : getters, setters, equals, toString ....
@Document
public class Message implements Serializable {

    private Long messageId;

    private String content;
    private LocalDateTime sentDate;

}
