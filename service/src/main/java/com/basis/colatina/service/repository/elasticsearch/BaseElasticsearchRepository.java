package com.basis.colatina.service.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseElasticsearchRepository<D, E> extends ElasticsearchRepository<D, E> {
    Class<D> getEntityRepository();
}
