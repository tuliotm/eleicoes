package com.eleicoes.api.repository;

import com.eleicoes.api.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CargoRepositoty extends JpaRepository<Cargo, UUID> {
}
