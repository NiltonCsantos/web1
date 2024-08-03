package br.com.web1.noticias.service.token.impl;

import io.jsonwebtoken.Jwts;
import br.com.web1.noticias.model.UsuarioEntidade;
import br.com.web1.noticias.service.token.TokenService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public TokenServiceImpl(){
        Calendar calendar= Calendar.getInstance();
    }

    @Override
    public String gerarToken(UsuarioEntidade usuarioEntidade) {

        try {
            return   Jwts.builder()
                    .setIssuer("auth-web1-noticias-app")
                    .setSubject(usuarioEntidade.getUsuTxLogin())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+36000000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS512).
                    compact();

        }catch (JwtException e){

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public String validarToken(String token) {

        return null;

    }

    @Override
    public String gerarTokenTemporario() {
        return null;
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


}
