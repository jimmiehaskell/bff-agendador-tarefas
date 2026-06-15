package dev.jimmiehaskell.bffagendador.infrastructure.client;

import dev.jimmiehaskell.bffagendador.business.dto.in.EnderecoDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.in.LoginRequestDTO;
import dev.jimmiehaskell.bffagendador.business.dto.in.TelefoneDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.in.UsuarioDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.out.EnderecoDTOResponse;
import dev.jimmiehaskell.bffagendador.business.dto.out.TelefoneDTOResponse;
import dev.jimmiehaskell.bffagendador.business.dto.out.UsuarioDTOResponse;
import dev.jimmiehaskell.bffagendador.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                           @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginRequestDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                        @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                        @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable String cep);
}
