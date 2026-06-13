package dev.jimmiehaskell.bffagendador.business;

import dev.jimmiehaskell.bffagendador.business.dto.in.TarefaDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.out.TarefaDTOResponse;
import dev.jimmiehaskell.bffagendador.infrastructure.client.TarefaClient;
import dev.jimmiehaskell.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaClient tarefaClient;

    public TarefaDTOResponse gravarTarefa(String token, TarefaDTORequest dto) {
        return tarefaClient.gravarTarefa(dto, token);
    }

    public List<TarefaDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                   LocalDateTime dataFinal,
                                                                   String token) {
        return tarefaClient.buscaListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefaClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefaClient.deletaTarefaPorId(id, token);
    }

    public TarefaDTOResponse alteraTarefaStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefaClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefaDTOResponse updateTarefa(TarefaDTORequest dto, String id, String token) {
        return tarefaClient.updateTarefa(dto, id, token);
    }
}
