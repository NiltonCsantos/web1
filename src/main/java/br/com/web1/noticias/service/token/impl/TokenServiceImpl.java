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
    public String gerarAcessToken(UsuarioEntidade usuarioEntidade) {
            return   Jwts.builder()
                    .setIssuer("auth-web1-noticias-app")
                    .setSubject(usuarioEntidade.getUsuTxLogin())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+86400000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS512).
                    compact();
    }

    @Override
    public String gerarRefreshToken(UsuarioEntidade usuarioEntidade) {
        return   Jwts.builder()
                .setIssuer("auth-web1-noticias-app")
                .setSubject(usuarioEntidade.getUsuTxLogin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+864000000))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).
                compact();
    }

    @Override
    public String validarToken(String token) {


        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build().
                parseClaimsJws(token).
                getBody().
                getSubject();

    }



    @Override
    public String gerarTokenTemporario(UsuarioEntidade usuarioEntidade) {
        return   Jwts.builder()
                .setIssuer("auth-web1-noticias-app")
                .setSubject(usuarioEntidade.getUsuTxLogin())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).
                compact();
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


}
