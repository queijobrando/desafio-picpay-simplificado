package com.simplificado.picpay.picpaysimplificado.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacao")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carteira pagador;

    @ManyToOne(fetch = FetchType.LAZY)
    private Carteira beneficiario;

    @CreatedDate
    private LocalDateTime criadoEm;
}
