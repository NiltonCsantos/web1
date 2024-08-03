package br.com.web1.noticias.config.security;

import br.com.web1.noticias.config.handler.RestErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        RestErrorMessage restErrorMessage = new RestErrorMessage();
        Throwable cause = authException.getCause();

        if (cause instanceof ExpiredJwtException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            restErrorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
            restErrorMessage.setMessage("Token expirado.");
        } else if (cause instanceof MalformedJwtException) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            restErrorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            restErrorMessage.setMessage("Token malformado.");
        } else if (cause instanceof SignatureException) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            restErrorMessage.setStatus(HttpStatus.FORBIDDEN.value());
            restErrorMessage.setMessage("Token inválido.");
        } else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            restErrorMessage.setStatus(HttpStatus.FORBIDDEN.value());
            restErrorMessage.setMessage("Você precisa estar autenticado para acessar esse recurso.");
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(restErrorMessage));
    }
}
