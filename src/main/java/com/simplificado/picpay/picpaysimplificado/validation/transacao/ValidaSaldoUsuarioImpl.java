package com.simplificado.picpay.picpaysimplificado.validation.transacao;

import com.simplificado.picpay.picpaysimplificado.dto.transacao.TransacaoDto;
import com.simplificado.picpay.picpaysimplificado.entity.Carteira;
import com.simplificado.picpay.picpaysimplificado.exceptions.ValidacaoTransacaoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidaSaldoUsuarioImpl implements TransacaoValidationStrategy {

    @Override
    public void validar(TransacaoDto transacaoDto, Carteira pagadorCarteira) {
        if (!possuiSaldoSuficiente(pagadorCarteira, transacaoDto.valor())){
            throw new ValidacaoTransacaoException("Saldo insuficiente");
        }
    }

    public boolean possuiSaldoSuficiente(Carteira carteira, BigDecimal valor) {
        return carteira.getSaldo().compareTo(valor) >= 0;
    }
}
