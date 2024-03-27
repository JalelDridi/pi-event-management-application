package org.example.notificationmodule.entities;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.notificationmodule.enums.DeliveryChannel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data // Lombok annotation to get rid of most boilerplate code like : getters, setters, equals, toString ....
@AllArgsConstructor
@Document
public class Notification implements Serializable {

    @Id
    private Long notificationId;
    private String eventType;
    private Boolean isRead;
    private DeliveryChannel deliveryChannel ;
    private Long messageId;
    private Long userId;

}
