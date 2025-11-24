package com.simplificado.picpay.picpaysimplificado.validation.transacao;

import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoDto;
import com.simplificado.picpay.picpaysimplificado.entity.Carteira;

public interface TransacaoValidationStrategy {

    void validar(TransacaoDto transacaoDto, Carteira carteira);
}
