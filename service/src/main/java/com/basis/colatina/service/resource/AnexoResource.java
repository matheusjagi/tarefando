package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.AnexoService;
import com.basis.colatina.service.service.dto.AnexoDTO;
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
@RequestMapping("/api/anexo")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    @GetMapping
    public ResponseEntity<List<AnexoDTO>> getAll(){
        List<AnexoDTO> anexos = anexoService.getAll();
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> getById(@PathVariable("id") Long idAnexo){
        return new ResponseEntity<>(anexoService.getById(idAnexo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnexoDTO> save(@RequestBody AnexoDTO anexoDTO){
        return new ResponseEntity<>(anexoService.save(anexoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long idAnexo){
        anexoService.remove(idAnexo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
