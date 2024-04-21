package tn.esprit.eventmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventModuleApplication.class, args);
    }

}
