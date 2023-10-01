package com.eleicoes.api.service;

import com.eleicoes.api.model.Candidato;
import com.eleicoes.api.model.Eleitor;
import com.eleicoes.api.model.Voto;
import com.eleicoes.api.repository.CandidatoRepository;
import com.eleicoes.api.repository.EleitorRepository;
import com.eleicoes.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VotoService {

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    EleitorRepository eleitorRepository;

    public Voto votar(UUID idCandidato, UUID idEleitor) {
        Candidato candidato = candidatoRepository.findById(idCandidato)
                .orElseThrow(() -> new RuntimeException("Candidato não encontrado com o ID: " + idCandidato));

        Eleitor eleitor = eleitorRepository.findById(idEleitor)
                .orElseThrow(() -> new RuntimeException("Eleitor não encontrado com o ID: " + idEleitor));

        Voto voto = new Voto();
        voto.setCandidato(candidato);
        voto.setEleitor(eleitor);
        voto.setData(LocalDateTime.now());

        return votoRepository.save(voto);
    }
}

