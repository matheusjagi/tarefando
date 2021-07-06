package com.basis.colatina.service.service.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument;
import com.basis.colatina.service.repository.ResponsavelRepository;
import com.basis.colatina.service.repository.elasticsearch.ResponsavelRepositorySearch;
import com.basis.colatina.service.service.event.ResponsavelEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event){
        log.debug("Reindex em Responsavel com id: {}", event.getId());
        ResponsavelDocument document = responsavelRepository.getDocument(event.getId());
        responsavelRepositorySearch.save(document);
    }
}
