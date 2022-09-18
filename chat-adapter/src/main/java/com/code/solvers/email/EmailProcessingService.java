package com.code.solvers.email;

import com.code.solvers.model.EmailContent;
import com.code.solvers.queue.HtmlEmailBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailProcessingService {
    Logger logger = Logger.getLogger(EmailProcessingController.class);

    private final String templateName = "MoMEmail";

    @Autowired
    private HtmlEmailBuilder emailBuilder;

    @Autowired
    private JavaMailSender mailSender;


    public String sendEmail(String subject, String[] toEmail, String participants, List<String> summaryPoints,
                            List<String> actionItems) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
            messageHelper.setFrom("Credit Suisse Team<solvers2022@outlook.com>");
            messageHelper.setTo(toEmail);
            messageHelper.setSubject(subject);
            Context context = new Context();
            context.setVariable("summaryPoints", summaryPoints);
            context.setVariable("participants", participants);
            context.setVariable("actionItems", actionItems);

            messageHelper.setText(emailBuilder.build(templateName, context), true);
            mailSender.send(mail);

            return "success";

        } catch(Exception e) {
            logger.error("Error sending email :"+e);
        }

        return "Sorry. Couldn't send the email right now. I will try again back after sometime.";
    }

    public void sendEmail(EmailContent emailContent, String[] emailIDs) {

        sendEmail(emailContent.getSubject(), emailIDs, emailContent.getParticipants(),
                    emailContent.getSummaryPoints(), emailContent.getActionItems());

    }

    public List<String> getSummaryPoints() {
        List<String> summaryPoints = new ArrayList<>();
        summaryPoints.add("They are having breakfast with Brett and his business partner Marsellus Wallace");
        summaryPoints.add("They're having Big Kahuna Burger from the Hawaiian burger joint");
        summaryPoints.add("Jules takes a bite of Brett's burger\\n* Vincent is not hungry");
        return summaryPoints;
    }

    public String getParticipants() {
        return "Aashirwad, Anooj, Gishnu, Sunil";
    }

    public List<String> getActionItems() {
        List<String> actionItems = new ArrayList<>();
        actionItems.add("@Anooj needs to deploy by today EOD");
        actionItems.add("@Gishnu will go ahead with finalized approach & will document");
        return actionItems;
    }
}
