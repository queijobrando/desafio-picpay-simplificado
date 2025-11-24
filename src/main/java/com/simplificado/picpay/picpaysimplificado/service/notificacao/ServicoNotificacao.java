package com.simplificado.picpay.picpaysimplificado.service.notificacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "notificacao-service", url = "${notificacao.service.url}")
public interface ServicoNotificacao {

    @GetMapping
    ResponseEntity<Void> isNotificado();
}
