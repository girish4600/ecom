package com.gsk.notification.service;

import com.gsk.notification.email.EmailTemplate;
import com.gsk.notification.kafka.order.Product;
import com.gsk.notification.kafka.payment.PaymentConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gsk.notification.email.EmailTemplate.ORDER_CONFIRMATION;
import static com.gsk.notification.email.EmailTemplate.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinationEmail, String customerName, Double amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper  = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("girish4600@gmail.com");
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> vars = new HashMap<>();
        vars.put("customerName", customerName);
        vars.put("amount", amount);
        vars.put("orderReference", orderReference);

        Context context =  new Context();
        context.setVariables(vars);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException ex){
            log.warn("WARNING - cannot send email to {}",destinationEmail);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destinationEmail, String customerName, Long amount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper  = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("girish4600@gmail.com");
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> vars = new HashMap<>();
        vars.put("customerName", customerName);
        vars.put("totalAmount", amount);
        vars.put("orderReference", orderReference);
        vars.put("products", products);

        Context context =  new Context();
        context.setVariables(vars);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());
        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s , ", destinationEmail, templateName));
        } catch (MessagingException ex){
            log.warn("WARNING - cannot send email to {}",destinationEmail);
        }
    }

}
