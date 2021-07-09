package com.basis.colatina.service.repository.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResponsavelRepositorySearch extends BaseElasticsearchRepository<ResponsavelDocument, Long> {

    @Override
    default Class<ResponsavelDocument> getEntityRepository() {
        return ResponsavelDocument.class;
    }
}
