package com.basis.colatina.service.repository.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.AnexoDocument;

public interface AnexoRepositorySearch extends BaseElasticsearchRepository<AnexoDocument, Long> {

    @Override
    default Class<AnexoDocument> getEntityRepository() {
        return AnexoDocument.class;
    }
}
