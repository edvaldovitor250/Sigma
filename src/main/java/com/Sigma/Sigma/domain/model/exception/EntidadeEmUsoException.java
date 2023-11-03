package com.Sigma.Sigma.domain.model.exception;

public class EntidadeEmUsoException extends RuntimeException {

    public EntidadeEmUsoException(String id){
        super("A entidade com ID " + id + " está em uso e não pode ser removida.");
    }

}