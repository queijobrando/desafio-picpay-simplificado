package com.simplificado.picpay.picpaysimplificado.controller;

import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraDto;
import com.simplificado.picpay.picpaysimplificado.dto.carteira.CarteiraInfoDto;
import com.simplificado.picpay.picpaysimplificado.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/carteira")
public class CarteiraController implements GenericController{

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<CarteiraInfoDto> criarCarteira(@Valid @RequestBody CarteiraDto carteiraDto){
        CarteiraInfoDto carteiraInfoDto = carteiraService.criarCarteira(carteiraDto);

        URI location = generateHeaderLocationLong(carteiraInfoDto.id());

        return ResponseEntity.created(location).body(carteiraInfoDto);
    }
}
