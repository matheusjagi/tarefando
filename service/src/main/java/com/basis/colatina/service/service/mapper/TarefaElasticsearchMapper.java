package com.basis.colatina.service.service.mapper;

import com.basis.colatina.service.domain.elasticsearch.TarefaDocument;
import com.basis.colatina.service.service.dto.TarefaListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaElasticsearchMapper extends EntityMapper<TarefaListagemDTO, TarefaDocument> {

}
