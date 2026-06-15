package dev.jimmiehaskell.bffagendador.infrastructure.client;

import dev.jimmiehaskell.bffagendador.business.dto.in.TarefaDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.out.TarefaDTOResponse;
import dev.jimmiehaskell.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefa", url = "${agendador-tarefa.url}")
public interface TarefaClient {
    @PostMapping
    TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest dto,
                                   @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaDTOResponse> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscaListaTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
        @RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    TarefaDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                              @RequestParam("id") String id,
                                              @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    TarefaDTOResponse updateTarefa(@RequestBody TarefaDTORequest dto,
                                   @RequestParam("id") String id,
                                   @RequestHeader(name = "Authorization", required = false) String token);
}
