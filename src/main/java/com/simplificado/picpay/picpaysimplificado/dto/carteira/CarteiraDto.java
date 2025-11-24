package com.simplificado.picpay.picpaysimplificado.dto.carteira;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CarteiraDto(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String email,
        @NotNull
        Long tipoId
) {
}
