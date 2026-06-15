package dev.jimmiehaskell.bffagendador.business;

import dev.jimmiehaskell.bffagendador.business.dto.in.EnderecoDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.in.LoginRequestDTO;
import dev.jimmiehaskell.bffagendador.business.dto.in.TelefoneDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.in.UsuarioDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.out.EnderecoDTOResponse;
import dev.jimmiehaskell.bffagendador.business.dto.out.TelefoneDTOResponse;
import dev.jimmiehaskell.bffagendador.business.dto.out.UsuarioDTOResponse;
import dev.jimmiehaskell.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest) {
        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

    public String loginUsuario(LoginRequestDTO loginRequestDTO) {
        return usuarioClient.login(loginRequestDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTORequest, String token) {
        return usuarioClient.atualizaEndereco(enderecoDTORequest, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTORequest, String token) {
        return usuarioClient.atualizaTelefone(telefoneDTORequest, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {
        return usuarioClient.cadastraTelefone(dto, token);
    }
}
