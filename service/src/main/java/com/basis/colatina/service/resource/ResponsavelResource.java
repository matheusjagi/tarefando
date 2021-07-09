package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.ResponsavelService;
import com.basis.colatina.service.service.dto.ResponsavelDTO;
import com.basis.colatina.service.service.dto.ResponsavelListagemDTO;
import com.basis.colatina.service.service.elasticsearch.ResponsavelElasticsearchService;
import com.basis.colatina.service.service.filter.ResponsavelFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/responsavel")
@RequiredArgsConstructor
@Slf4j
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    private final ResponsavelElasticsearchService responsavelElasticsearchService;

    @PostMapping("/search")
    public ResponseEntity<Page<ResponsavelListagemDTO>> search(@RequestBody ResponsavelFilter filter, Pageable pageable){
        log.debug("REST request to search for a page of Responsavel to query {}", filter);
        Page<ResponsavelListagemDTO> page = responsavelElasticsearchService.search(filter, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> getAll(){
        List<ResponsavelDTO> responsaveis = responsavelService.getAll();
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> getById(@PathVariable("id") Long idResponsavel){
        return new ResponseEntity<>(responsavelService.getById(idResponsavel), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO> save(@RequestBody ResponsavelDTO responsavelDTO){
        return new ResponseEntity<>(responsavelService.save(responsavelDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long idResponsavel){
        responsavelService.remove(idResponsavel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
