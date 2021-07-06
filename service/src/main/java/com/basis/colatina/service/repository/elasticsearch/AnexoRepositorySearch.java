package com.basis.colatina.service.repository.elasticsearch;

import com.basis.colatina.service.domain.elasticsearch.AnexoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AnexoRepositorySearch extends ElasticsearchRepository<AnexoDocument, Long> {
}
