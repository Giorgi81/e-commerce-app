package com.services.notification.service;

import com.services.notification.dto.Product;
import com.services.notification.enums.EmailTemplete;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public void paymentConfirmationSuccessNotification(
            String destination,
            String customerName,
            BigDecimal quantity,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());


        mimeMessageHelper.setFrom("support@andersenlab.com");
        final String templateName = EmailTemplete.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables =  new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("quantity", quantity);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);


        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(destination);
            mimeMessageHelper.setSubject(EmailTemplete.PAYMENT_CONFIRMATION.getSubject());
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new MessagingException("Failed to send email", e);
        }


    }


    @Async
    public void orderConfirmationSuccessNotification(
            String destination,
            String customerName,
            BigDecimal quantity,
             String orderReference,
            List<Product> products
    ) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("quantity", quantity);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        mimeMessageHelper.setFrom("support@andersenlab.com");

        final String templateName = EmailTemplete.ORDER_CONFIRMATION.getTemplate();

        Context context = new Context();
        context.setVariables(variables);

        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(destination);
            mimeMessageHelper.setSubject(EmailTemplete.ORDER_CONFIRMATION.getSubject());
            javaMailSender.send(mimeMessage);
        }catch(MessagingException e) {
            throw new MessagingException("Failed to send email", e);

        }
    }






}