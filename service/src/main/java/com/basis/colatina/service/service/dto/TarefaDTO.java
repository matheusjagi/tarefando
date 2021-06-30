package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TarefaDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate data_inicio_prevista;

    private LocalDate data_termino_prevista;

    private LocalDate data_inicio;

    private LocalDate data_termino;

    private String tipo;

    private String status;

    private String comentarios;

    private Long idResponsavel;
}
