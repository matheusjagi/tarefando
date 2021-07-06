package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.AnexoService;
import com.basis.colatina.service.service.TarefaService;
import com.basis.colatina.service.service.dto.TarefaAnexosDTO;
import com.basis.colatina.service.service.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
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

    private final AnexoService anexoService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> getAll(){
        List<TarefaDTO> tarefas = tarefaService.getAll();
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> getById(@PathVariable("id") Long idTarefa){
        return new ResponseEntity<>(tarefaService.getById(idTarefa), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TarefaAnexosDTO tarefaAnexosDTO){
        TarefaDTO tarefaDTO = tarefaService.save(tarefaAnexosDTO.getTarefaDTO());
        tarefaAnexosDTO.getAnexosDTO().forEach(anexo -> anexo.setTarefaId(tarefaDTO.getId()));
        anexoService.saveAll(tarefaAnexosDTO.getAnexosDTO());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long idTarefa){
        tarefaService.remove(idTarefa);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
