package com.eleicoes.api.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Candidato implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "idCargo")
    private Cargo cargo;

    private String nome;

    private Integer numero;

    private String legenda;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    private LocalDateTime alteradoEm;

}
