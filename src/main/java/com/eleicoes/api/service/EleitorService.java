package com.eleicoes.api.service;

import com.eleicoes.api.model.Eleitor;
import com.eleicoes.api.repository.EleitorRepository;
import com.eleicoes.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EleitorService {

    @Autowired
    EleitorRepository eleitorRepository;

    @Autowired
    VotoRepository votoRepository;

    public Eleitor criar(Eleitor eleitor) {
        Optional<Eleitor> eleitorExistente = eleitorRepository.findByCpf(eleitor.getCpf());
        if (eleitorExistente.isPresent()) {
            throw new RuntimeException("Eleitor já existe");
        }
        return eleitorRepository.save(eleitor);
    }

    public List<Eleitor> listarTodos() {

        return eleitorRepository.findAll();
    }

    public Optional<Eleitor> procurarPeloId(UUID id) {

        return eleitorRepository.findById(id);
    }

    public void deletar(UUID id) {
        Optional<Eleitor> eleitor = eleitorRepository.findById(id);

        if (!eleitor.isPresent()) {
            throw new RuntimeException("Eleitor não encontrado com o ID: " + id);
        }

        if (votoRepository.existsByEleitor(eleitor.get())) {
            throw new RuntimeException("Não é possível deletar o eleitor com o ID: " + id + " pois ele já votou.");
        }

        eleitorRepository.delete(eleitor.get());
    }

    public Eleitor atualizar(UUID id, Eleitor novoEleitor) {
        return eleitorRepository.findById(id)
                .map(eleitorExistente -> {
                    eleitorExistente.setNome(novoEleitor.getNome());
                    eleitorExistente.setCpf(novoEleitor.getCpf());
                    eleitorExistente.setAlteradoEm(LocalDateTime.now());
                    return eleitorRepository.save(eleitorExistente);
                })
                .orElseThrow(() -> new RuntimeException("Eleitor não encontrado"));
    }
}
