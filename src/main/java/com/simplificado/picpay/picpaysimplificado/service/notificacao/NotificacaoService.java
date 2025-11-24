package com.simplificado.picpay.picpaysimplificado.service.notificacao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificacaoService {

    private final ServicoNotificacao servicoNotificacao;

    public NotificacaoService(ServicoNotificacao servicoNotificacao) {
        this.servicoNotificacao = servicoNotificacao;
    }

    public void sendNotificacao() {
        try{
            var resp = servicoNotificacao.isNotificado();

            if (resp.getStatusCode().is2xxSuccessful()) {
                log.info("Notificado com sucesso");
                return;
            }

            log.error("Erro na notificação");
        }
        catch (feign.FeignException e){
            log.error(e.getMessage());
        }
    }
}
