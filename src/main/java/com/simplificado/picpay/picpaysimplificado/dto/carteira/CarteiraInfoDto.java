package com.simplificado.picpay.picpaysimplificado.dto.carteira;

import java.math.BigDecimal;

public record CarteiraInfoDto(
        Long id,
        String nome,
        String cpf,
        String email,
        String senha,
        BigDecimal saldo,
        Long tipoId
) {
}
