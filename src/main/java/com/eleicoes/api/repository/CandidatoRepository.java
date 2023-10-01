package com.eleicoes.api.repository;

import com.eleicoes.api.model.Candidato;
import com.eleicoes.api.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
    Optional<Candidato> findByNumero(Integer numero);

    List<Candidato> findByCargo(Cargo cargo);
}

