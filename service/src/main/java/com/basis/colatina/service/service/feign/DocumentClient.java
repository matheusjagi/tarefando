package com.basis.colatina.service.service.feign;

import com.basis.colatina.service.service.dto.DocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
@RequestMapping("/api/minio")
public interface DocumentClient {

    @GetMapping("/{uuid}")
    DocumentDTO getByUUID(@PathVariable("uuid") String uuid);

    @PostMapping
    String save(@RequestBody DocumentDTO documentDTO);

    @DeleteMapping("/{uuid}")
    void remove(@PathVariable("uuid") String uuid);
}
