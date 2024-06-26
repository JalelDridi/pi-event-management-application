package tn.esprit.notificationmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableMongoRepositories
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class NotificationModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationModuleApplication.class, args);
    }

}
