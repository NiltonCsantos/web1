package br.com.web1.noticias.service.mail.impl;

import br.com.web1.noticias.service.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Async
@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {


    @Value("${support.mail}")
    private String supportEmail;
    @Value("${apiUrl}")
    private String apiUrl;

    private  final JavaMailSender mailSender;

    private  final TemplateEngine templateEngine;

    @Async
    @Override
    public void sendMail(String email, String usuTxNome, String urlTokenTemp){

        try {

            final MimeMessage mimeMessage= this.mailSender.createMimeMessage();

            final MimeMessageHelper emailHelper;

            emailHelper= new MimeMessageHelper(mimeMessage, true, "UTF-8");

            emailHelper.setTo(email);
            emailHelper.setSubject("Boas vindas");
            emailHelper.setFrom(supportEmail);

            final Context context= new Context();

            context.setVariable("fullName", usuTxNome);
            context.setVariable("confirmationUrl", apiUrl);

            final String htmlContent= this.templateEngine.process("email", context);

            emailHelper.setText(htmlContent, true);


            mailSender.send(mimeMessage);


            System.out.println("Email enviado");




        }
        catch (MessagingException e){

            throw new RuntimeException();

        }
    }

}
