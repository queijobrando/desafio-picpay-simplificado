package com.simplificado.picpay.picpaysimplificado.exceptions;

public class TransacaoNaoAutorizadaException extends RuntimeException {
    public TransacaoNaoAutorizadaException() {
        super("Transação não autorizada");
    }
}
