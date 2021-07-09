package com.basis.colatina.service.repository;

import com.basis.colatina.service.domain.Tarefa;
import com.basis.colatina.service.domain.elasticsearch.TarefaDocument;
import com.basis.colatina.service.repository.elasticsearch.BaseElasticsearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("select new com.basis.colatina.service.domain.elasticsearch.TarefaDocument(t.id, " +
            "t.titulo, " +
            "t.dataInicioPrevista, " +
            "t.dataTerminoPrevista, " +
            "t.dataInicio, " +
            "t.dataTermino, " +
            "t.tipo," +
            "t.status, " +
            "t.responsavel.id) " +
            "from Tarefa t " +
            "where t.id = :id")
    TarefaDocument getDocument(@Param("id") Long id);

    @Query("select new com.basis.colatina.service.domain.elasticsearch.TarefaDocument(t.id, " +
            "t.titulo, " +
            "t.dataInicioPrevista, " +
            "t.dataTerminoPrevista, " +
            "t.dataInicio, " +
            "t.dataTermino, " +
            "t.tipo," +
            "t.status, " +
            "t.responsavel.id) " +
            "from Tarefa t " +
            "order by t.id")
    Page<TarefaDocument> reindexQuery(Pageable pageable);
}
