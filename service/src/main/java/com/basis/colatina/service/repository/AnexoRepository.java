package com.basis.colatina.service.repository;

import com.basis.colatina.service.domain.Anexo;
import com.basis.colatina.service.domain.elasticsearch.AnexoDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Long> {

    @Query("select new com.basis.colatina.service.domain.elasticsearch.AnexoDocument(" +
            "a.id, " +
            "a.titulo, " +
            "a.hash, " +
            "a.tamanho, " +
            "a.tipo, " +
            "a.tarefa.id) " +
            "from Anexo a " +
            "where a.id = :id")
    AnexoDocument getDocument(Long id);

}
