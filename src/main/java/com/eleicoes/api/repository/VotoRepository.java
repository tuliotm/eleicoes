package com.eleicoes.api.repository;

import com.eleicoes.api.model.Candidato;
import com.eleicoes.api.model.Eleitor;
import com.eleicoes.api.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotoRepository extends JpaRepository<Voto, UUID> {

    boolean existsByCandidato(Candidato candidato);

    boolean existsByEleitor(Eleitor eleitor);

    long countByCandidato(Candidato candidato);
}



