package com.basis.colatina.service.domain.elasticsearch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "tarefando-tarefa")
public class TarefaDocument extends BaseDocument{

    protected static final String SORT = "sort";
    protected static final String DATE_PATTERN = "dd-MM-yyyy";

    @MultiField(mainField = @Field(type = FieldType.Text, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Text, store = true)})
    private String titulo;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataInicioPrevista;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataTerminoPrevista;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataInicio;

    @MultiField(mainField = @Field(type = FieldType.Keyword, store = true),
            otherFields = {@InnerField(suffix = SORT, type = FieldType.Date, store = true, format = DateFormat.custom, pattern = DATE_PATTERN)})
    private String dataTermino;

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
        this.dataInicioPrevista = dataInicioPrevista.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.dataTerminoPrevista = dataTerminoPrevista.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.dataInicio = dataInicio.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.dataTermino = dataTermino.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
        this.tipo = tipo;
        this.status = status;
        this.responsavelId = responsavelId;
    }
}
