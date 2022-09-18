package com.code.solvers.email;

import com.code.solvers.queue.HtmlEmailBuilder;
import com.code.solvers.queue.RocketAdapterQueueEndpoint;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RestController("/chat/adapter")
public class EmailProcessingController {
    Logger logger = Logger.getLogger(EmailProcessingController.class);

    @Autowired
    private HtmlEmailBuilder emailBuilder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProcessingService emailProcessingService;

    private Map<String, String> USER_EMAIL_ID_STORE;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    private String sendEmail(String subject, String toEmail, String userId) {
        try {
            subject = "TEST";
            toEmail = "anooj.chavda@gmail.com";
            userId = "anooj";

            emailProcessingService.sendEmail(subject, toEmail, userId);

            return "Email sent successfully";
        } catch(Exception e) {
            logger.error("Error sending email :"+e);
        }

        return "Sorry. Couldn't send the email right now. I will try again back after sometime.";
    }
}
