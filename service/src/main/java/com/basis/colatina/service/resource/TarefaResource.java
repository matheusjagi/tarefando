package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.TarefaService;
import com.basis.colatina.service.service.dto.TarefaAnexosDTO;
import com.basis.colatina.service.service.dto.TarefaDTO;
import com.basis.colatina.service.service.dto.TarefaListagemDTO;
import com.basis.colatina.service.service.elasticsearch.TarefaElasticsearchService;
import com.basis.colatina.service.service.filter.TarefaFilter;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
public class TarefaResource {

    private final TarefaService tarefaService;

    private final TarefaElasticsearchService tarefaElasticsearchService;

    @GetMapping("/search")
    public ResponseEntity<Page<TarefaListagemDTO>> search(@RequestBody TarefaFilter filter, Pageable pageable){
        Page<TarefaListagemDTO> page = tarefaElasticsearchService.search(filter, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TarefaListagemDTO>> getAll(){
        List<TarefaListagemDTO> tarefas = tarefaService.getAll();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> getById(@PathVariable("id") Long idTarefa){
        return new ResponseEntity<>(tarefaService.getById(idTarefa), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TarefaAnexosDTO tarefaAnexosDTO){
        tarefaService.save(tarefaAnexosDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/teste")
    public ResponseEntity<Void> saveTarefa(@RequestBody TarefaDTO tarefaDTO){
        tarefaService.saveTarefa(tarefaDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long idTarefa){
        tarefaService.remove(idTarefa);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
