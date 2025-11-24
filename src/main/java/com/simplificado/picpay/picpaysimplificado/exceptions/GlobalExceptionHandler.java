package com.simplificado.picpay.picpaysimplificado.exceptions;

import com.simplificado.picpay.picpaysimplificado.dto.exception.CamposErros;
import com.simplificado.picpay.picpaysimplificado.dto.exception.RespostaErro;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public RespostaErro handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<CamposErros> listaDeErros = fieldErrors
                .stream()
                .map(fe -> new CamposErros(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new RespostaErro(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                listaDeErros
        );
    }

    @ExceptionHandler(TransacaoNaoAutorizadaException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RespostaErro handleTransacaoNaoAutorizadaException(TransacaoNaoAutorizadaException e){
        return RespostaErro.unauthorized(e.getMessage());
    }

    @ExceptionHandler(ValidacaoTransacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro handleValidacaoTransacaoException(ValidacaoTransacaoException e){
        return RespostaErro.badRequest(e.getMessage());
    }

    @ExceptionHandler(ValidacaoCarteiraException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespostaErro handleValidacaoCarteiraException(ValidacaoCarteiraException e){
        return RespostaErro.badRequest(e.getMessage());
    }

    @ExceptionHandler(CarteiraNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RespostaErro handleCarteiraNaoEncontradaException(CarteiraNaoEncontradaException e){
        return RespostaErro.notFound(e.getMessage());
    }

    @ExceptionHandler(TipoCarteiraNaoEncontradaEXception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RespostaErro handleTipoCarteiraNaoEncontradaException(TipoCarteiraNaoEncontradaEXception e){
        return RespostaErro.notFound(e.getMessage());
    }
}
