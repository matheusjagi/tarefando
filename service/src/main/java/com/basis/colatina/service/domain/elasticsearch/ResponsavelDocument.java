package com.basis.colatina.service.domain.elasticsearch;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import java.time.LocalDate;

@Document(indexName = "tarefando-responsavel")
public class ResponsavelDocument extends BaseDocument{

    protected static final String SORT = "sort";

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String nome;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String email;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "dd/mm/yyyy")})
    private LocalDate dataNascimento;

    @MultiField(mainField = @Field(type = FieldType.Boolean, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Boolean, store = true)})
    private boolean situacao;

    public ResponsavelDocument(Long id, String nome, String email, LocalDate dataNascimento, boolean situacao) {
        super(id);
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.situacao = situacao;
    }
}
