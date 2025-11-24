package com.simplificado.picpay.picpaysimplificado;

import com.simplificado.picpay.picpaysimplificado.service.autorizador.ServicoAutorizador;
import com.simplificado.picpay.picpaysimplificado.service.notificacao.ServicoNotificacao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(clients = {
        ServicoAutorizador.class,
        ServicoNotificacao.class
}
)
public class PicpaySimplificadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicpaySimplificadoApplication.class, args);
    }

}
