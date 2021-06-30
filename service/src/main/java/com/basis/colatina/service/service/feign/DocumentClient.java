package com.basis.colatina.service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface DocumentClient {

    @GetMapping("/api/upload")
    String upload();
}
