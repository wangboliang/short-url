package com.shorturl.controller;

import com.shorturl.domain.Response;
import com.shorturl.domain.UrlDTO;
import com.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlController {

    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private UrlService urlService;

    @GetMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/r/{id}")
    public ResponseEntity redirect(@PathVariable("id") String id) {
        String longUrl = urlService.getLongUrlById(id);
        if (StringUtils.isEmpty(longUrl)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create(longUrl)).build();
    }

    @PostMapping("/getShortUrl")
    @ResponseBody
    public ResponseEntity<Response> getShortUrl(@RequestBody UrlDTO urlDTO, HttpServletRequest request) {
        if (StringUtils.isEmpty(urlDTO.getLongUrl())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

//        String host = "/";
//        int serverPort = request.getServerPort();
//        if (serverPort == 80 || serverPort == 443) {
//            host = request.getScheme() + "://" + request.getServerName();
//        } else {
//            host = request.getScheme() + "://" + request.getServerName() + ":" + serverPort;
//        }
//        StringBuffer requestURL = request.getRequestURL();
//        String host = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).toString();
        String urlPrefix = "http://" + host + ":" + port + contextPath + "/r/";
        urlDTO.setUrlPrefix(urlPrefix);
        String shortUrl = urlService.getAndSaveShortUrl(urlDTO);
        return ResponseEntity.ok(Response.success(shortUrl));
    }

    @PostMapping("/getLongUrl")
    @ResponseBody
    public ResponseEntity<Response> getLongUrl(@RequestBody UrlDTO urlDTO) {
        if (StringUtils.isEmpty(urlDTO.getId()) && StringUtils.isEmpty(urlDTO.getShortUrl())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String longUrl = null;
        if (!StringUtils.isEmpty(urlDTO.getId())) {
            longUrl = urlService.getLongUrlById(urlDTO.getId());
        } else if (!StringUtils.isEmpty(urlDTO.getShortUrl())) {
            longUrl = urlService.getLongUrlByShortUrl(urlDTO.getShortUrl());
        }
        return ResponseEntity.ok(Response.success(longUrl));
    }
}
