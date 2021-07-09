package com.basis.colatina.service.service.mapper;

import com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument;
import com.basis.colatina.service.service.dto.ResponsavelListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelElasticsearchMapper extends EntityMapper<ResponsavelListagemDTO, ResponsavelDocument> {
}
