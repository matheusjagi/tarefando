package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO {

    private Long id;

    private String nome;

    private String email;

    private LocalDate data_nascimento;

    private boolean situacao;
}
