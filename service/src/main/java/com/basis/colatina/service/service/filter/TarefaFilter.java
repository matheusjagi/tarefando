package com.basis.colatina.service.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import java.io.Serializable;

@Getter
@Setter
public class TarefaFilter implements Serializable, BaseFilter {

    private String query;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        addMustTermQuery(queryBuilder, "titulo", query);

        return queryBuilder;
    }
}
