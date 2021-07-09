package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TarefaListagemDTO implements Serializable {

    private Long id;

    private String titulo;

    private String descricao;

    private String dataInicioPrevista;

    private String dataTerminoPrevista;

    private String dataInicio;

    private String dataTermino;

    private String tipo;

    private String status;

    private String nomeResponsavel;
}
