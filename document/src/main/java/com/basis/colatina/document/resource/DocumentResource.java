package com.basis.colatina.document.resource;

import com.basis.colatina.document.service.DocumentService;
import com.basis.colatina.document.service.dto.DocumentDTO;
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

@RestController
@RequestMapping("/api/minio")
@RequiredArgsConstructor
public class DocumentResource {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody DocumentDTO documentDTO){
        documentService.saveDocument(documentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DocumentDTO> getByUUID(@PathVariable("uuid") String uuid){
        return new ResponseEntity<>(documentService.getDocument(uuid), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> remove(@PathVariable("uuid") String uuid){
        documentService.removeDocument(uuid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
