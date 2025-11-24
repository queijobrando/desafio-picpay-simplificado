package com.simplificado.picpay.picpaysimplificado.exceptions;

public class TipoCarteiraNaoEncontradaEXception extends RuntimeException {
    public TipoCarteiraNaoEncontradaEXception() {
        super("Tipo de carteira inválido ou inexistente");
    }
}
