package br.com.web1.noticias.service.mail;

public interface MailService {
    void  sendMail(String email, String usuTxNome, String urlTokenTemp);
}
