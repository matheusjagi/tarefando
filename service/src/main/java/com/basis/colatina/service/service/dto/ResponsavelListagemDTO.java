package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelListagemDTO implements Serializable {

    private Long id;

    private String nome;

    private String email;

    private String dataNascimento;

    private boolean situacao;
}
