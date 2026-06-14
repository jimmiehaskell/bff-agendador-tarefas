package dev.jimmiehaskell.bffagendador.business;

import dev.jimmiehaskell.bffagendador.business.dto.in.LoginRequestDTO;
import dev.jimmiehaskell.bffagendador.business.dto.out.TarefaDTOResponse;
import dev.jimmiehaskell.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(convertFromRequestDTO());
        log.info("Iniciada a busca de tarefas.");
        LocalDateTime horaAtualCinto = LocalDateTime.now().plusMinutes(5);
        LocalDateTime horaFutura = horaAtualCinto.plusHours(1);
        List<TarefaDTOResponse> listaTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(
            horaAtualCinto, horaFutura, token);

        log.info("Tarefas encontradas: {}", listaTarefas.toString());
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado o usuário: {}", tarefa.getEmailUsuario());
            tarefaService.alteraTarefaStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
        log.info("Tarefa enviada com sucesso!");
    }

    public String login(LoginRequestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO convertFromRequestDTO() {
        return LoginRequestDTO.builder()
            .email(email)
            .senha(senha)
            .build();
    }
}
