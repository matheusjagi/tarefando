package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TarefaDTO implements Serializable {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate dataInicioPrevista;

    private LocalDate dataTerminoPrevista;

    private LocalDate dataInicio;

    private LocalDate dataTermino;

    private String tipo;

    private String status;

    private Long responsavelId;
}
