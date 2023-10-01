package com.eleicoes.api.service;

import com.eleicoes.api.model.Cargo;
import com.eleicoes.api.repository.CargoRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CargoService {

    @Autowired
    CargoRepositoty repository;

    public Cargo criar(Cargo cargo) {
        return repository.save(cargo);
    }

    public List<Cargo> listarTodos() {

        return repository.findAll();
    }

    public Optional<Cargo> procurarPeloId(UUID id) {

        return repository.findById(id);
    }

    public void delete(UUID id) {

        repository.deleteById(id);
    }

    public Cargo atualizar(UUID id, Cargo novoCargo) {
        return repository.findById(id)
                .map(cargoExistente -> {
                    cargoExistente.setNome(novoCargo.getNome());
                    cargoExistente.setAlteradoEm(LocalDateTime.now());
                    return repository.save(cargoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
    }
}
