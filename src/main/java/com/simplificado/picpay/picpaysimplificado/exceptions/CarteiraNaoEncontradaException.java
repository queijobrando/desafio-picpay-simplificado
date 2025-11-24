package com.simplificado.picpay.picpaysimplificado.exceptions;

public class CarteiraNaoEncontradaException extends RuntimeException {
    public CarteiraNaoEncontradaException() {
        super("Carteira inválida ou inexistente");
    }
}
