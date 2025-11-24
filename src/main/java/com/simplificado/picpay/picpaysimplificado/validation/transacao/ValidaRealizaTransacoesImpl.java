package com.simplificado.picpay.picpaysimplificado.validation.transacao;

import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoDto;
import com.simplificado.picpay.picpaysimplificado.entity.Carteira;
import com.simplificado.picpay.picpaysimplificado.exceptions.ValidacaoTransacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidaRealizaTransacoesImpl implements TransacaoValidationStrategy{

    @Override
    public void validar(TransacaoDto transacaoDto, Carteira pagadorCarteira) {
        if (!validaRealizaTransacoes(pagadorCarteira)) {
            throw new ValidacaoTransacaoException("Tipo da carteira não autorizado a realizar transações");
        }
    }

    boolean validaRealizaTransacoes(Carteira carteira){
        return carteira.getTipo().isRealizaTransacoes();
    }
}
