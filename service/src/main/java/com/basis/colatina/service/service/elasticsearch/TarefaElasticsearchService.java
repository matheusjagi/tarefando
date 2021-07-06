package com.basis.colatina.service.service.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.TarefaDocument;
import com.basis.colatina.service.repository.TarefaRepository;
import com.basis.colatina.service.repository.elasticsearch.TarefaRepositorySearch;
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
public class TarefaElasticsearchService {

    private final TarefaRepository tarefaRepository;

    private final TarefaRepositorySearch tarefaRepositorySearch;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(TarefaEvent event){
        log.debug("Reindex em Tarefa com id: {}", event.getId());
        TarefaDocument document = tarefaRepository.getDocument(event.getId());
        tarefaRepositorySearch.save(document);
    }
}
