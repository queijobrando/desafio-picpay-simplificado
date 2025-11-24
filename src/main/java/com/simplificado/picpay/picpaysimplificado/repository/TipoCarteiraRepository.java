package com.simplificado.picpay.picpaysimplificado.repository;

import com.simplificado.picpay.picpaysimplificado.entity.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCarteiraRepository extends JpaRepository<TipoCarteira, Long> {
    Optional<TipoCarteira> findByNome(String usuario);
}
