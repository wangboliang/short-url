package com.shorturl.mapper;

import com.shorturl.entity.UrlDO;

public interface UrlMapper {
    int save(UrlDO urlDO);

    UrlDO selectById(String id);

    String selectLongUrlById(String id);

    String selectLongUrlByShortUrl(String shortUrl);

    void clearExpiredData();
}
