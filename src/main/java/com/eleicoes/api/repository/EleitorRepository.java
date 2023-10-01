package com.eleicoes.api.repository;

import com.eleicoes.api.model.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, UUID> {
    Optional<Eleitor> findByCpf(String cpf);
}
