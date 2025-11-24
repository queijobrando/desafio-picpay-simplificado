package com.simplificado.picpay.picpaysimplificado.service.autorizador;

import com.simplificado.picpay.picpaysimplificado.dto.autorizador.AuthorizationResponseDTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "autorizador-service", url = "${autorizador.service.url}")
public interface ServicoAutorizador {

    @GetMapping
    ResponseEntity<AuthorizationResponseDTo> isAutorizado();
}

