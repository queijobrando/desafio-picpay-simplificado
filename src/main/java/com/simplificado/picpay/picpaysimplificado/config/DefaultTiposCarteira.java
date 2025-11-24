package com.simplificado.picpay.picpaysimplificado.config;

import com.simplificado.picpay.picpaysimplificado.entity.TipoCarteira;
import com.simplificado.picpay.picpaysimplificado.repository.TipoCarteiraRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DefaultTiposCarteira implements CommandLineRunner {

    private final TipoCarteiraRepository tipoCarteiraRepository;

    public DefaultTiposCarteira(TipoCarteiraRepository tipoCarteiraRepository) {
        this.tipoCarteiraRepository = tipoCarteiraRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (tipoCarteiraRepository.findByNome("Usuario").isEmpty()) {
            TipoCarteira tipoCarteira = new TipoCarteira();
            tipoCarteira.setNome("Usuario");
            tipoCarteira.setRealizaTransacoes(true);
            tipoCarteiraRepository.save(tipoCarteira);
        } else {
            log.info("Tipo de carteira Usuario ja existente");
        }

        if (tipoCarteiraRepository.findByNome("Lojista").isEmpty()) {
            TipoCarteira tipoCarteira = new TipoCarteira();
            tipoCarteira.setNome("Lojista");
            tipoCarteira.setRealizaTransacoes(false);
            tipoCarteiraRepository.save(tipoCarteira);
        } else {
            log.info("Tipo de carteira Lojista ja existente");
        }
    }
}
