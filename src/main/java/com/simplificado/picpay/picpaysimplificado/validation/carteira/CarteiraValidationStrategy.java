package com.simplificado.picpay.picpaysimplificado.validation.carteira;

import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraDto;

public interface CarteiraValidationStrategy {
    void validar(CarteiraDto carteiraDto);
}
