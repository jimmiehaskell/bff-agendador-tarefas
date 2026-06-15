package dev.jimmiehaskell.bffagendador.infrastructure.client.config;

import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.BusinessException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.ConflictException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.IllegalArgumentException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String mensagemErro = mensagemErro(response);

        return switch (response.status()) {
            case 400 -> new IllegalArgumentException("$$ Erro: " + mensagemErro);
            case 401 -> new UnauthorizedException("$$ Erro: " + mensagemErro);
            case 403 -> new ResourceNotFoundException("$$ Erro: " + mensagemErro);
            case 409 -> new ConflictException("$$ Erro: " + mensagemErro);
            default -> new BusinessException("$$ Erro: " + mensagemErro);
        };
    }

    private String mensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}