package com.shorturl.controller;

import com.shorturl.domain.Response;
import com.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class XXLJobController {

    @Autowired
    private UrlService urlService;

    @RequestMapping("/clearExpiredData")
    public ResponseEntity clearExpiredData() {
        urlService.clearExpiredData();
        return ResponseEntity.ok(Response.success());
    }
}
