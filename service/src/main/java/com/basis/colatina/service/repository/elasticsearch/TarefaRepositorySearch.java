package com.basis.colatina.service.repository.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.TarefaDocument;

public interface TarefaRepositorySearch extends BaseElasticsearchRepository<TarefaDocument, Long> {

    @Override
    default Class<TarefaDocument> getEntityRepository() {
        return TarefaDocument.class;
    }
}
