package com.simplificado.picpay.picpaysimplificado.validation.carteira;

import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraDto;
import com.simplificado.picpay.picpaysimplificado.exceptions.ValidacaoCarteiraException;
import com.simplificado.picpay.picpaysimplificado.repository.CarteiraRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidaEmailJaExisteImpl implements CarteiraValidationStrategy{

    private final CarteiraRepository carteiraRepository;

    public ValidaEmailJaExisteImpl(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public void validar(CarteiraDto carteiraDto) {
        if (carteiraRepository.existsByEmail(carteiraDto.email())){
            throw new ValidacaoCarteiraException("Já existe uma carteira com esse Email");
        }
    }
}
