package com.bkartisan.be.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;


@Service
public class EmailUtil {

    private JavaMailSender mailSender;
    private FileLoaderUtil fileLoader;

    @Autowired
    public EmailUtil(JavaMailSender mailSender, FileLoaderUtil fileLoader) {
        this.mailSender = mailSender;
        this.fileLoader = fileLoader;
    }

    // TODO: make sending email asynchronous
    @Async
    public void sendVerificationEmailToSeller(String receiver, Integer otp) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            String htmlMsg = fileLoader.loadHtmlTemplateInString();
            htmlMsg = htmlMsg.replace("{receiver}", receiver);
            htmlMsg = htmlMsg.replace("{otp}", otp.toString());


            // mimeMessageHelper.setFrom();
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject("[BK Artisan] Mã xác nhận trở thành người bán");
            mimeMessageHelper.setText(htmlMsg, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
