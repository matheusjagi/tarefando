package com.basis.colatina.service.builder;

public interface CustomizacaoEntidade<E> {

    void executar(E entidade);
}