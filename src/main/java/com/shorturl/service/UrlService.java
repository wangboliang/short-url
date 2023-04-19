package com.shorturl.service;

import com.shorturl.domain.UrlDTO;

public interface UrlService {

    String getLongUrlById(String id);

    String getLongUrlByShortUrl(String shortUrl);

    String getAndSaveShortUrl(UrlDTO urlDTO);

    void clearExpiredData();
}
