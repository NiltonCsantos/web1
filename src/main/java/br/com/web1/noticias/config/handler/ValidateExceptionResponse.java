package br.com.web1.noticias.config.handler;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public non-sealed class ValidateExceptionResponse extends RestErrorMessage {

    private List<Field> fields;

    @Data
    @Builder
    public static class Field {
        public String fieldName;
        public String fieldErrorMessage;
    }
}
