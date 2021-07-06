package com.basis.colatina.service.service.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.AnexoDocument;
import com.basis.colatina.service.domain.elasticsearch.TarefaDocument;
import com.basis.colatina.service.repository.AnexoRepository;
import com.basis.colatina.service.repository.TarefaRepository;
import com.basis.colatina.service.repository.elasticsearch.AnexoRepositorySearch;
import com.basis.colatina.service.repository.elasticsearch.TarefaRepositorySearch;
import com.basis.colatina.service.service.event.AnexoEvent;
import com.basis.colatina.service.service.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AnexoElasticsearchService {

    private final AnexoRepository anexoRepository;

    private final AnexoRepositorySearch anexoRepositorySearch;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(AnexoEvent event){
        log.debug("Reindex em Anexo com id: {}", event.getId());
        AnexoDocument document = anexoRepository.getDocument(event.getId());
        anexoRepositorySearch.save(document);
    }
}
