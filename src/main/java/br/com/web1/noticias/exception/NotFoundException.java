package br.com.web1.noticias.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message + " Não encontrado");
    }
}
