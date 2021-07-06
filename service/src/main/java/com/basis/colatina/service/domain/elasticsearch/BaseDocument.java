package com.basis.colatina.service.domain.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.Setting;

@Getter
@Setter
@Setting(settingPath = "config/elasticsearch/elasticsearch-config.json")
@AllArgsConstructor
@NoArgsConstructor
public class BaseDocument {

    protected static final String TRIM_CASE_INSENSITIVE = "trim_case_insensitive";
    protected static final String SORT = "sort";

    @MultiField(mainField = @Field(type = FieldType.Long, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private Long id;
}
