package br.com.web1.noticias.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;


@Setter
@Getter
public class JwtExecption extends AuthenticationException {
    public JwtExecption(String msg, Throwable cause) {super(msg, cause);}
}