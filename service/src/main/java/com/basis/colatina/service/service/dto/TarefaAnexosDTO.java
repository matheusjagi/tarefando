package com.basis.colatina.service.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TarefaAnexosDTO implements Serializable {

    private TarefaDTO tarefaDTO;

    private List<AnexoDTO> anexosDTO;
}
