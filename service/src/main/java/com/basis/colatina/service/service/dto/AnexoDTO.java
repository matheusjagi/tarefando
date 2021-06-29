package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnexoDTO {

    private Long id;

    private String titulo;

    private byte[] hash;

    private Long tamanho;

    private String tipo;

    private Long idTarefa;
}
