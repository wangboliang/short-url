package com.shorturl.service.impl;

import com.shorturl.domain.UrlDTO;
import com.shorturl.entity.UrlDO;
import com.shorturl.mapper.UrlMapper;
import com.shorturl.service.UrlService;
import com.shorturl.util.DateUtil;
import com.shorturl.util.ShortUrlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {

    private static final int DEFAULT_VALID_DAYS = 30;
    @Autowired
    private UrlMapper urlMapper;

    @Override
    public String getLongUrlById(String id) {
        //url decode
        return decodeUrl(urlMapper.selectLongUrlById(id));
    }

    @Override
    public String getLongUrlByShortUrl(String shortUrl) {
        //url decode
        return decodeUrl(urlMapper.selectLongUrlByShortUrl(shortUrl));
    }

    @Override
    public String getAndSaveShortUrl(UrlDTO urlDTO) {
        // url encode
        String longUrl = encodeUrl(urlDTO.getLongUrl());
        int length = urlDTO.getLength() != null ? urlDTO.getLength() : ShortUrlGenerator.DEFAULT_LENGTH;
        String id = ShortUrlGenerator.generateShortUrlId(longUrl, length);

        UrlDO urlDO = urlMapper.selectById(id);
        // if it exists, return
        if (urlDO != null && !StringUtils.isEmpty(urlDO.getShortUrl())) {
            return urlDO.getShortUrl();
        }

        // 构造新的 DO 对象
        urlDO = buildUrlDO(id, longUrl, urlDTO);

        try {
            urlMapper.save(urlDO);
        } catch (Exception e) {
            log.error("Failed to save short url for id: {}", id, e);
        }

        return urlDO.getShortUrl();
    }

    private UrlDO buildUrlDO(String id, String longUrl, UrlDTO urlDTO) {
        UrlDO urlDO = new UrlDO();
        urlDO.setId(id);
        urlDO.setLongUrl(longUrl);
        urlDO.setShortUrl(urlDTO.getUrlPrefix() + id);
        Date nowDate = new Date(); // 复用当前时间戳
        urlDO.setCreatedDate(nowDate);

        // valid day default 30
        int validDays = urlDTO.getValidDays() != null && urlDTO.getValidDays() > 0
                ? urlDTO.getValidDays()
                : DEFAULT_VALID_DAYS;

        urlDO.setExpiresDate(DateUtil.addDayOfYear(nowDate, validDays));
        return urlDO;
    }

    @Override
    public void clearExpiredData() {
        urlMapper.clearExpiredData();
    }

    private String encodeUrl(String url) {
        try {
            if (url.contains("?")) {
                String host = url.substring(0, url.indexOf("?"));
                String param = url.substring(url.indexOf("?") + 1);
                url = host + "?" + URLEncoder.encode(param, "UTF-8");
            }
        } catch (Exception e) {
            log.error("url encode error : ", e);
        }
        return url;
    }

    private String decodeUrl(String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            log.error("url decode error : ", e);
        }
        return url;
    }
}
