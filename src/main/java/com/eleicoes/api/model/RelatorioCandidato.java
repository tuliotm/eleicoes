package com.eleicoes.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioCandidato {

    private UUID idCargo;
    private String nomeCargo;
    private long votos;
    private UUID idCandidatoVencedor;
    private String nomeCandidatoVencedor;

}
