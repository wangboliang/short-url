package com.shorturl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UrlDO {
    private String id;
    private String longUrl;
    private String shortUrl;
    private Date createdDate;
    private Date expiresDate;
}
