package br.com.web1.noticias.service.authentication.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationForm(
        @NotBlank(message = "o campo login é obrigatório")
        @Size(min = 8, message = "o campo login deve ter no mínimo 8 carácteres")
        String usuTxLogin,
        @NotBlank(message = "o campo senha é obrigatório")
        @Size(min = 8, message = "o campo senha deve ter no mínimo 8 carácteres")
        String usuTxSenha
) {
}
