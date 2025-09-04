package com.excelr.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String subject, String body, String to, File file) {
        
        try {
            jakarta.mail.internet.MimeMessage mimeMsg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

            helper.setSubject(subject);
            helper.setText(body, true); 
            helper.setTo(to);
            helper.addAttachment("Plans-Info", file);

            mailSender.send(mimeMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
