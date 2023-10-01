package com.eleicoes.api.controller;

import com.eleicoes.api.model.Candidato;
import com.eleicoes.api.model.RelatorioCandidato;
import com.eleicoes.api.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    CandidatoService candidatoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Candidato> criar(@RequestBody Candidato candidato) {
        Candidato candidatoCriado = candidatoService.criar(candidato);

        return ResponseEntity.status(201).body(candidatoCriado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Candidato> atualizar(@PathVariable UUID id, @RequestBody Candidato candidato) {
        try {
            Candidato candidatoAtualizado = candidatoService.atualizar(id, candidato);
            return ResponseEntity.ok(candidatoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Candidato> listarTodos() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Candidato> procurarPeloId(@PathVariable UUID id) {
        return candidatoService.procurarPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        candidatoService.deletar(id);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioCandidato>> gerarRelatorio() {
        List<RelatorioCandidato> relatorio = candidatoService.gerarRelatorio();
        return ResponseEntity.ok(relatorio);
    }
}
