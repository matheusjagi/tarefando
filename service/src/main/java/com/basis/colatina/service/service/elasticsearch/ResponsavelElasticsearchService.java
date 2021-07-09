package com.basis.colatina.service.service.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument;
import com.basis.colatina.service.repository.ResponsavelRepository;
import com.basis.colatina.service.repository.elasticsearch.ResponsavelRepositorySearch;
import com.basis.colatina.service.service.dto.ResponsavelDTO;
import com.basis.colatina.service.service.dto.ResponsavelListagemDTO;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.event.ResponsavelEvent;
import com.basis.colatina.service.service.filter.ResponsavelFilter;
import com.basis.colatina.service.service.filter.TarefaFilter;
import com.basis.colatina.service.service.mapper.ResponsavelElasticsearchMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ResponsavelElasticsearchService {

    private final ResponsavelRepository responsavelRepository;

    private final ResponsavelRepositorySearch responsavelRepositorySearch;

    private final ResponsavelElasticsearchMapper responsavelElasticsearchMapper;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event){
        log.debug("Reindex em Responsavel com id: {}", event.getId());
        ResponsavelDocument document = responsavelRepository.getDocument(event.getId());
        responsavelRepositorySearch.save(document);
    }

    public Page<ResponsavelListagemDTO> search(ResponsavelFilter filter, Pageable pageable) {
        return responsavelRepositorySearch.search(filter.getFilter(), pageable).map(responsavelElasticsearchMapper::toDto);
    }
}
