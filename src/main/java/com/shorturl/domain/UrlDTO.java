package com.shorturl.domain;

import lombok.Data;

@Data
public class UrlDTO {
    private String id;
    private String host;
    private String longUrl;
    private String shortUrl;
    private int validDays;
}
