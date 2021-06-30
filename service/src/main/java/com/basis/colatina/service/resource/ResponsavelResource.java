package com.basis.colatina.service.resource;

import com.basis.colatina.service.service.ResponsavelService;
import com.basis.colatina.service.service.dto.ResponsavelDTO;
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
@RequestMapping("/api/responsavel")
@RequiredArgsConstructor
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

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
