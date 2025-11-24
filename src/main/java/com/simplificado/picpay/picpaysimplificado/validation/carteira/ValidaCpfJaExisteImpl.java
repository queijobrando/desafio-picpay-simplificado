package com.simplificado.picpay.picpaysimplificado.validation.carteira;

import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraDto;
import com.simplificado.picpay.picpaysimplificado.exceptions.ValidacaoCarteiraException;
import com.simplificado.picpay.picpaysimplificado.repository.CarteiraRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidaCpfJaExisteImpl implements CarteiraValidationStrategy{

    private final CarteiraRepository carteiraRepository;

    public ValidaCpfJaExisteImpl(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    @Override
    public void validar(CarteiraDto carteiraDto) {
        if (carteiraRepository.existsByCpf(carteiraDto.cpf())){
            throw new ValidacaoCarteiraException("Já existe uma carteira com esse CPF");
        }
    }
}
