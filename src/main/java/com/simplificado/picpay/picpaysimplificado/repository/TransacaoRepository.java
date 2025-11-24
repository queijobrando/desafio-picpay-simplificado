package com.simplificado.picpay.picpaysimplificado.repository;

import com.simplificado.picpay.picpaysimplificado.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
