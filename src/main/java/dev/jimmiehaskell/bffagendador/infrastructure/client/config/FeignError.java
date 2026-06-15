package dev.jimmiehaskell.bffagendador.infrastructure.client.config;

import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.BusinessException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.ConflictException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import dev.jimmiehaskell.bffagendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 401 -> new UnauthorizedException("Erro usuário não autorizado");
            case 403 -> new ResourceNotFoundException("Erro atributo não encontrado");
            case 409 -> new ConflictException("Erro atributo já existe");
            default -> new BusinessException("Erro de servidor: " + response.status());
        };
    }
}