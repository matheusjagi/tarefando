package com.basis.colatina.service.domain.elasticsearch;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import java.time.LocalDate;

@Document(indexName = "tarefando-tarefa")
public class TarefaDocument extends BaseDocument{

    protected static final String SORT = "sort";

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String titulo;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "dd/mm/yyyy")})
    private LocalDate dataInicioPrevista;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "dd/mm/yyyy")})
    private LocalDate dataTerminoPrevista;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "dd/mm/yyyy")})
    private LocalDate dataInicio;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "dd/mm/yyyy")})
    private LocalDate dataTermino;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String tipo;

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String status;

    private Long responsavelId;

    public TarefaDocument(Long id, String titulo, LocalDate dataInicioPrevista, LocalDate dataTerminoPrevista, LocalDate dataInicio, LocalDate dataTermino, String tipo, String status, Long responsavelId) {
        super(id);
        this.titulo = titulo;
        this.dataInicioPrevista = dataInicioPrevista;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.tipo = tipo;
        this.status = status;
        this.responsavelId = responsavelId;
    }
}
