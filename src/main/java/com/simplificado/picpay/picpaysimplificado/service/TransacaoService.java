package com.simplificado.picpay.picpaysimplificado.service;

import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoDto;
import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoInfoDto;
import com.simplificado.picpay.picpaysimplificado.entity.Carteira;
import com.simplificado.picpay.picpaysimplificado.entity.Transacao;
import com.simplificado.picpay.picpaysimplificado.exceptions.TransacaoNaoAutorizadaException;
import com.simplificado.picpay.picpaysimplificado.repository.TransacaoRepository;
import com.simplificado.picpay.picpaysimplificado.service.autorizador.AutorizadorService;
import com.simplificado.picpay.picpaysimplificado.service.notificacao.NotificacaoService;
import com.simplificado.picpay.picpaysimplificado.validation.transacao.TransacaoValidationStrategy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {

    private final CarteiraService carteiraService;
    private final AutorizadorService autorizadorService;
    private final NotificacaoService notificacaoService;
    private final List<TransacaoValidationStrategy> validationStrategies;
    private final TransacaoRepository transacaoRepository;

    public TransacaoService(CarteiraService carteiraService, AutorizadorService autorizadorService, NotificacaoService notificacaoService, List<TransacaoValidationStrategy> validationStrategies, TransacaoRepository transacaoRepository) {
        this.carteiraService = carteiraService;
        this.autorizadorService = autorizadorService;
        this.notificacaoService = notificacaoService;
        this.validationStrategies = validationStrategies;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public TransacaoInfoDto realizarTransacao(TransacaoDto transacaoDto){
        Carteira pagadorCarteira = carteiraService.getCarteira(transacaoDto.pagador());

        Carteira beneficiarioCarteira = carteiraService.getCarteira(transacaoDto.beneficiario());

        validationStrategies.forEach(s -> s.validar(transacaoDto, pagadorCarteira));
        var statusAutorizacao = autorizadorService.isAutorizado();

        if (!statusAutorizacao) {
            throw new TransacaoNaoAutorizadaException();
        }

        pagadorCarteira.setSaldo(pagadorCarteira.getSaldo().subtract(transacaoDto.valor()));
        beneficiarioCarteira.setSaldo(beneficiarioCarteira.getSaldo().add(transacaoDto.valor()));

        carteiraService.salvarCarteira(pagadorCarteira);
        carteiraService.salvarCarteira(beneficiarioCarteira);

        Transacao transacao = criarTransacao(pagadorCarteira, beneficiarioCarteira, transacaoDto.valor());
        notificacaoService.sendNotificacao();

        return new TransacaoInfoDto(transacao.getId(), transacaoDto.valor(), pagadorCarteira.getId(), beneficiarioCarteira.getId(), transacao.getCriadoEm());
    }

    @Transactional
    public Transacao criarTransacao(
            Carteira pagadorCarteira,
            Carteira beneficiarioCarteira,
            BigDecimal valor
    ) {
        Transacao transacao = new Transacao();
        transacao.setBeneficiario(beneficiarioCarteira);
        transacao.setPagador(pagadorCarteira);
        transacao.setValor(valor);

        transacaoRepository.save(transacao);
        return transacao;
    }

}
