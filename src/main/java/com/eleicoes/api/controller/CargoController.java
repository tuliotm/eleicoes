package com.eleicoes.api.controller;

import com.eleicoes.api.model.Cargo;
import com.eleicoes.api.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    CargoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cargo> criar(@RequestBody Cargo cargo) {
        Cargo cargoCriado = service.criar(cargo);

        return ResponseEntity.status(201).body(cargoCriado);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cargo> atualizar(@PathVariable UUID id, @RequestBody Cargo cargo) {
        try {
            Cargo cargoAtualizado = service.atualizar(id, cargo);
            return ResponseEntity.ok(cargoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<Cargo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cargo> procurarPeloId(@PathVariable UUID id) {
        return service.procurarPeloId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
