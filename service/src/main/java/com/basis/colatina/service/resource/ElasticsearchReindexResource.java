package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.elasticsearch.ElasticsearchReindexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elasticsearch/reindex")
@RequiredArgsConstructor
public class ElasticsearchReindexResource {

    private final ElasticsearchReindexService elasticsearchReindexService;

    @GetMapping
    public ResponseEntity<String> reindex() {
        this.elasticsearchReindexService.reindex();
        return ResponseEntity.ok().body("Reindexing all elasticsearch...");
    }

    @GetMapping("/{entity}")
    public ResponseEntity<String> reindexEntity(@PathVariable String entity) {
        this.elasticsearchReindexService.reindexEntity(entity);
        return ResponseEntity.ok().body("Reindexing entity elasticsearch...");
    }

}
