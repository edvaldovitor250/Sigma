package com.Sigma.Sigma.domain.model.exception;

public class EntidadeNaoEncontradaException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
