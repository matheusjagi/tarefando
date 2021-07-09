package com.basis.colatina.service.service.mapper;

import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.dto.TarefaListagemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaListagemMapper extends EntityMapper<TarefaListagemDTO, Tarefa> {

    @Override
    @Mapping(source = "nomeResponsavel", target = "responsavel.nome")
    Tarefa toEntity(TarefaListagemDTO dto);

    @Override
    @InheritInverseConfiguration
    TarefaListagemDTO toDto(Tarefa entity);
}
