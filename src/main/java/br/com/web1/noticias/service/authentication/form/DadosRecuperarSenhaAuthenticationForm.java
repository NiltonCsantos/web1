package br.com.web1.noticias.service.authentication.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Locale;

public record DadosRecuperarSenhaAuthenticationForm(
        @NotNull(message = "o campo data de nascimento é obrigatório")
        LocalDate usuDtNascimento,
        @NotBlank(message = "o campo email é obrigatório")
        @Email(message = "email inválido")
        String usuTxEmail,

        @NotBlank(message = "o campo nova senha é obrigatório")
        @Size(min = 8, message = "o campo nova senha deve ter no mínimo 8 caracteres")
        String usuTxNovaSenha
) {
}
