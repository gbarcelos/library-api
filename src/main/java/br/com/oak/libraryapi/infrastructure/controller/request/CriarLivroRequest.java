package br.com.oak.libraryapi.infrastructure.controller.request;

import br.com.oak.libraryapi.application.livro.LivroInput;
import br.com.oak.libraryapi.domain.Livro;
import br.com.oak.libraryapi.infrastructure.annotation.UniqueValue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

public record CriarLivroRequest(

    @NotBlank
    String titulo,

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    BigDecimal preco,

    @NotBlank
    @ISBN(type = Type.ISBN_10)
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    String isbn
) implements LivroInput {

}
