package com.eleicoes.api.controller;

import com.eleicoes.api.model.Eleitor;
import com.eleicoes.api.model.Voto;
import com.eleicoes.api.service.EleitorService;
import com.eleicoes.api.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/eleitores")
public class EleitorController {

    @Autowired
    EleitorService eleitorService;

    @Autowired
    VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Eleitor> criar(@RequestBody Eleitor eleitor) {
        Eleitor eleitorCriado = eleitorService.criar(eleitor);

        return ResponseEntity.status(201).body(eleitorCriado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Eleitor> atualizar(@PathVariable UUID id, @RequestBody Eleitor eleitor) {
        try {
            Eleitor eleitorAtualizado = eleitorService.atualizar(id, eleitor);
            return ResponseEntity.ok(eleitorAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Eleitor> listarTodos() {
        return eleitorService.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Eleitor> procurarPeloId(@PathVariable UUID id) {
        return eleitorService.procurarPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        eleitorService.deletar(id);
    }

    @PostMapping("/{id}/votar")
    public ResponseEntity<Voto> votar(@PathVariable UUID id, @RequestBody Map<String, UUID> payload) {
        UUID idCandidato = payload.get("idCandidato");
        Voto votoCriado = votoService.votar(idCandidato, id);

        return ResponseEntity.status(201).body(votoCriado);
    }
}
