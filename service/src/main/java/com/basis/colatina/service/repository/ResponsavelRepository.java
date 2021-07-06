package com.basis.colatina.service.repository;

import com.basis.colatina.service.domain.Responsavel;
import com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    @Query("select new com.basis.colatina.service.domain.elasticsearch.ResponsavelDocument(" +
            "r.id, " +
            "r.nome, " +
            "r.email, " +
            "r.dataNascimento, " +
            "r.situacao) " +
            "from Responsavel r " +
            "where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Long id);
}
