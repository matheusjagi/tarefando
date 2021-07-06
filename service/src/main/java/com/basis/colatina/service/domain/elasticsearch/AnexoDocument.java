package com.basis.colatina.service.domain.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

@Document(indexName = "tarefando-anexo")
public class AnexoDocument extends BaseDocument{

    protected static final String SORT = "sort";

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String titulo;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String hash;

    @MultiField(mainField = @Field(type = FieldType.Long, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private Long tamanho;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String tipo;

    private Long tarefaId;

    public AnexoDocument(Long id, String titulo, String hash, Long tamanho, String tipo, Long tarefaId) {
        super(id);
        this.titulo = titulo;
        this.hash = hash;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.tarefaId = tarefaId;
    }
}
