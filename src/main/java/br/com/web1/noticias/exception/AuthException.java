package br.com.web1.noticias.exception;

public class AuthException extends RuntimeException{
    public AuthException() {
        super("Usuário ou senha incorretos");
    }
}
