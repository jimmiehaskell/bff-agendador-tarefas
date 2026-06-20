package dev.jimmiehaskell.bffagendador.controller;

import dev.jimmiehaskell.bffagendador.business.dto.in.TarefaDTORequest;
import dev.jimmiehaskell.bffagendador.business.dto.out.TarefaDTOResponse;
import dev.jimmiehaskell.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import dev.jimmiehaskell.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public interface TarefaController {
    @PostMapping
    @Operation(summary = "Salvar tarefas de usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest dto,
                                                          @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    @Operation(summary = "Busca lista tarefas por email de usuário", description = "Lista de tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<List<TarefaDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período", description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<List<TarefaDTOResponse>> buscaListaTarefasPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
        @RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por ID", description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa ID não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                           @RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefas alteradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa ID não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<TarefaDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id,
                                                              @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    @Operation(summary = "Altera dados de tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    @ApiResponse(responseCode = "403", description = "Tarefa ID não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    ResponseEntity<TarefaDTOResponse> updateTarefa(@RequestBody TarefaDTORequest dto,
                                                   @RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token);
}
