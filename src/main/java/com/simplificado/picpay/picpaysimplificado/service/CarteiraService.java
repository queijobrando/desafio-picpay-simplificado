package com.simplificado.picpay.picpaysimplificado.service;

import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraDto;
import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraInfoDto;
import com.simplificado.picpay.picpaysimplificado.entity.Carteira;
import com.simplificado.picpay.picpaysimplificado.entity.TipoCarteira;
import com.simplificado.picpay.picpaysimplificado.exceptions.CarteiraNaoEncontradaException;
import com.simplificado.picpay.picpaysimplificado.exceptions.TipoCarteiraNaoEncontradaEXception;
import com.simplificado.picpay.picpaysimplificado.repository.CarteiraRepository;
import com.simplificado.picpay.picpaysimplificado.repository.TipoCarteiraRepository;
import com.simplificado.picpay.picpaysimplificado.validation.carteira.CarteiraValidationStrategy;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final List<CarteiraValidationStrategy> carteiraValidationStrategies;
    private final TipoCarteiraRepository tipoCarteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository, List<CarteiraValidationStrategy> carteiraValidationStrategies, TipoCarteiraRepository tipoCarteiraRepository) {
        this.carteiraRepository = carteiraRepository;
        this.carteiraValidationStrategies = carteiraValidationStrategies;
        this.tipoCarteiraRepository = tipoCarteiraRepository;
    }

    public Carteira getCarteira(Long idCarteira){
        return carteiraRepository.findById(idCarteira)
                .orElseThrow(CarteiraNaoEncontradaException::new);
    }

    @Transactional
    public void salvarCarteira(Carteira carteira){
        carteiraRepository.save(carteira);
    }

    @Transactional
    public CarteiraInfoDto criarCarteira(CarteiraDto carteiraDto){
        carteiraValidationStrategies.forEach(s -> s.validar(carteiraDto));
        TipoCarteira tipoCarteira = getTipoCarteira(carteiraDto.tipoId());

        Carteira carteira = new Carteira();
        carteira.setNome(carteiraDto.nome());
        carteira.setCpf(carteiraDto.cpf());
        carteira.setEmail(carteiraDto.email());
        carteira.setSenha(RandomStringUtils.secureStrong().nextAlphanumeric(5, 10));
        carteira.setTipo(tipoCarteira);

        carteiraRepository.save(carteira);
        return new CarteiraInfoDto(
                carteira.getId(),
                carteira.getNome(),
                carteira.getCpf(),
                carteira.getEmail(),
                carteira.getSenha(),
                carteira.getSaldo(),
                carteira.getTipo().getId()
        );
    }

    public TipoCarteira getTipoCarteira(Long idTipoCarteira){
        return tipoCarteiraRepository.findById(idTipoCarteira)
                .orElseThrow(TipoCarteiraNaoEncontradaEXception::new);
    }
}
