package com.simplificado.picpay.picpaysimplificado.dto.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoInfoDto(
        UUID id,
        BigDecimal valor,
        Long pagadorId,
        Long beneficiarioId,
        LocalDateTime criadoEm
) {
}
