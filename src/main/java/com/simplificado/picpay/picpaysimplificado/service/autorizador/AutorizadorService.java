package com.simplificado.picpay.picpaysimplificado.service.autorizador;

import org.springframework.stereotype.Service;

@Service
public class AutorizadorService {

    private final ServicoAutorizador servicoAutorizador;

    public AutorizadorService(ServicoAutorizador servicoAutorizador) {
        this.servicoAutorizador = servicoAutorizador;
    }

    public boolean isAutorizado() {

        try {
            var resp = servicoAutorizador.isAutorizado();

            if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                return resp.getBody().authorization();
            }

            return false;
        }
        catch(feign.FeignException feignException) {
            String msg = feignException.contentUTF8();

            return msg.contains("\"authorization\":true");
        }

    }

}
