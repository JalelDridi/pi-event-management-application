package tn.esprit.notificationmodule.entities;



import lombok.Data;
import tn.esprit.notificationmodule.enums.DeliveryChannel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data // Lombok annotation to get rid of most boilerplate code like : getters, setters, equals, toString ....
@Document(collection = "notifications")
public class Notification implements Serializable {

    @Transient
    public static final String SEQUENCE_NAME = "notifications_sequence";

    @Id
    private Long notificationId;

    private Boolean isSent;
    private Boolean isRead;
    private DeliveryChannel deliveryChannel ;
    private Long messageId;
    private String userId; // to

}
