package com.basis.colatina.service.service.elasticsearch;

import com.basis.colatina.service.repository.elasticsearch.BaseElasticsearchRepository;
import com.basis.colatina.service.repository.elasticsearch.Reindexer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticsearchReindexService {

    @Value("${elasticsearch.reindex.pageSize:10000}")
    private Integer pageSize;

    private final List<Reindexer> reindexadores;
    private final List<BaseElasticsearchRepository> repositories;
    private final ElasticsearchOperations elasticsearchOperations;

    @Transactional(readOnly = true)
    @Async
    public void reindex() {
        log.info("Starting reindex.");
        for (Reindexer reindexer : reindexadores) {
            reindex(reindexer);
        }
    }

    @Transactional(readOnly = true)
    @Async
    public void reindexEntity(String entity) {
        log.info("Starting reindex entity: {}", entity);
        for (Reindexer reindexer : reindexadores) {
            if(reindexer.getEntity().equals(entity)) {
                reindex(reindexer);
            }
        }
    }

    private void reindex(Reindexer bean) {
        Pageable pageable = PageRequest.of(0, pageSize);
        Page<?> page = bean.reindexPage(pageable);

        log.info("Objects found {}.", page.getTotalElements());

        if (!page.hasContent()) {
            return;
        }

        log.info("Total Pages {}.", page.getTotalPages());

        BaseElasticsearchRepository searchRepository = getSearchRepository(page);
        recreateIndexDocument(searchRepository.getEntityRepository());

        while (page.hasContent()) {
            log.info("Page Number {}.", page.getNumber());
            searchRepository.saveAll(page);
            page = bean.reindexPage(page.getPageable().next());
        }

        log.info("Finish reindex of {}.", bean.getEntity());
    }

    @SneakyThrows
    private BaseElasticsearchRepository getSearchRepository(Page<?> page) {
        Class documentClass = page.getContent().get(0).getClass();
        Iterator<BaseElasticsearchRepository> var3 = this.repositories.iterator();

        BaseElasticsearchRepository searchRepository;
        do {
            if (!var3.hasNext()) {
                throw new Exception("Falha de Reindexação...");
            }

            searchRepository = var3.next();
        } while(!searchRepository.getEntityRepository().equals(documentClass));

        return searchRepository;
    }

    private <T> void recreateIndexDocument(Class<T> entityClass) {
        log.info("Recriate index class: {}", entityClass.getName());
        elasticsearchOperations.indexOps(entityClass).delete();
        elasticsearchOperations.indexOps(entityClass).create();
//        elasticsearchOperations.indexOps(entityClass).putMapping();
    }
}
