package com.eleicoes.api.service;

import com.eleicoes.api.model.Candidato;
import com.eleicoes.api.model.Cargo;
import com.eleicoes.api.model.RelatorioCandidato;
import com.eleicoes.api.model.Voto;
import com.eleicoes.api.repository.CandidatoRepository;
import com.eleicoes.api.repository.CargoRepositoty;
import com.eleicoes.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    CargoRepositoty cargoRepositoty;

    public Candidato criar(Candidato candidato) {
        Optional<Candidato> candidatoExistente = candidatoRepository.findByNumero(candidato.getNumero());

        if (candidatoExistente.isPresent()) {
            throw new RuntimeException("Candidato já existe com o número: " + candidato.getNumero());
        }

        return candidatoRepository.save(candidato);
    }


    public List<Candidato> listarTodos() {

        return candidatoRepository.findAll();
    }

    public Optional<Candidato> procurarPeloId(UUID id) {

        return candidatoRepository.findById(id);
    }

    public void deletar(UUID id) {
        Optional<Candidato> candidato = candidatoRepository.findById(id);

        if (!candidato.isPresent()) {
            throw new RuntimeException("Candidato não encontrado com o ID: " + id);
        }

        if (votoRepository.existsByCandidato(candidato.get())) {
            throw new RuntimeException("Não é possível deletar o candidato com o ID: " + id + " pois ele já possui votos.");
        }

        candidatoRepository.delete(candidato.get());
    }

    public Candidato atualizar(UUID id, Candidato novoCandidato) {
        return candidatoRepository.findById(id)
                .map(candidatoExistente -> {
                    candidatoExistente.setCargo(novoCandidato.getCargo());
                    candidatoExistente.setNome(novoCandidato.getNome());
                    candidatoExistente.setNumero(novoCandidato.getNumero());
                    candidatoExistente.setLegenda(novoCandidato.getLegenda());
                    candidatoExistente.setAlteradoEm(LocalDateTime.now());
                    return candidatoRepository.save(candidatoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado"));
    }

    public List<RelatorioCandidato> gerarRelatorio() {
        List<RelatorioCandidato> relatorio = new ArrayList<>();

        List<Cargo> cargos = cargoRepositoty.findAll();

        for (Cargo cargo : cargos) {
            List<Candidato> candidatos = candidatoRepository.findByCargo(cargo);

            Candidato vencedor = null;
            long votosMax = 0;

            for (Candidato candidato : candidatos) {
                long votos = votoRepository.countByCandidato(candidato);

                if (votos > votosMax) {
                    vencedor = candidato;
                    votosMax = votos;
                }
            }

            if (vencedor != null) {
                relatorio.add(new RelatorioCandidato(cargo.getId(), cargo.getNome(), votosMax, vencedor.getId(), vencedor.getNome()));
            }
        }

        return relatorio;
    }
}
