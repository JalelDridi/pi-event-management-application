package org.example.notificationmodule.controllers;


import org.example.notificationmodule.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "initialTopic";

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Person person)
    {
        kafkaTemplate.send(TOPIC, "helloooo");
        return "Message published successfully by the producer !";
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Hellooooo";
    }
}
