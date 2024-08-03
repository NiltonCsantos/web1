package br.com.web1.noticias.exception;

public class RegistredException extends RuntimeException{

    public RegistredException(String message) {
        super(message + "já registrado");
    }
}
