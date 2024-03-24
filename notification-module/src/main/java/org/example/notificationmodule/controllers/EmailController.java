package org.example.notificationmodule.controllers;

import org.example.notificationmodule.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/email")
    public String publishMessage(@RequestParam String receiverMail)
    {
        emailService.sendEmail(receiverMail, "test", "This is a test message ");
        return String.format("Message sent successfully to %s", receiverMail);
    }

    @PostMapping("/emailhtml")
    public String publishMessageHtml(@RequestParam String receiverMail)
    {
        emailService.sendHtmlEmail();
        return String.format("Message sent successfully to %s", receiverMail);
    }

}
