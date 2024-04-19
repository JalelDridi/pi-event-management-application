package tn.esprit.notificationmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
@SpringBootApplication
public class NotificationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationModuleApplication.class, args);
    }

}
