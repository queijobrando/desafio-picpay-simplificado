package com.simplificado.picpay.picpaysimplificado.dto.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransacaoDto(
        @NotNull
        BigDecimal valor,
        @NotNull
        Long pagador,
        @NotNull
        Long beneficiario
) {
}
