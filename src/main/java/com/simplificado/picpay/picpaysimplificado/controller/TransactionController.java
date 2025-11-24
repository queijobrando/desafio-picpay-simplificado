package com.simplificado.picpay.picpaysimplificado.controller;

import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoDto;
import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoInfoDto;
import com.simplificado.picpay.picpaysimplificado.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/transfer")
public class TransactionController implements GenericController {

    private final TransacaoService transacaoService;

    public TransactionController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoInfoDto> realizarTransacao(@Valid @RequestBody TransacaoDto transacaoDto) throws IllegalAccessException {
        TransacaoInfoDto transacaoInfoDto = transacaoService.realizarTransacao(transacaoDto);

        URI location = generateHeaderLocationUuid(transacaoInfoDto.id());

        return ResponseEntity.created(location).body(transacaoInfoDto);
    }
}
