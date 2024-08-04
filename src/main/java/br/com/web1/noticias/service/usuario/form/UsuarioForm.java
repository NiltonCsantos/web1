package br.com.web1.noticias.service.usuario.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioForm(
        @NotBlank(message = "o campo nome é obrigatório")
        @Size(min = 8, message = "o campo login deve ter no mínimo 8 carácteres")
        String usuTxNome,
        @NotBlank(message = "o campo email é obrigatório")
        @Email(message = "o campo email está inválido")
        String usuTxEmail,
        @NotNull(message = "o campo data nascimento é obrigatório")
        LocalDate usutxDtNascimento,
        @NotBlank(message = "o campo login é obrigatório")
        String usuTxLogin,
        @NotBlank(message = "o campo senha é obrigatório")
        @Size(message = "o campo senha deve ter no mínimo 8 carácteres")
        String usuTxSenha,
        @NotBlank(message = "o campo telefone é obrigatório")
        @Size(min = 11, max = 11, message = "o campo telefone deve ter 11 caracteres")
        String usuTxTelefone
) {
}