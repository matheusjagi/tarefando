package com.basis.colatina.service.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaAnexosDTO implements Serializable {

    private TarefaDTO tarefaDTO;

    private List<AnexoDTO> anexosDTO;
}
