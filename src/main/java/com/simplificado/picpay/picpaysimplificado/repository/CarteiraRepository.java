package com.simplificado.picpay.picpaysimplificado.repository;

import com.simplificado.picpay.picpaysimplificado.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
