package com.simplificado.picpay.picpaysimplificado.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tipo_carteira")
@Data
public class TipoCarteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private boolean realizaTransacoes;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY)
    private List<Carteira> carteiras;

}
