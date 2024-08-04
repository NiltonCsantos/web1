package br.com.web1.noticias.service.authentication.dto;

import lombok.Builder;

@Builder
public record AuthenticationDto(
        String acessToken,
        String refreshToken
) {

}
