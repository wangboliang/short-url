package com.shorturl.service.impl;

import com.shorturl.domain.UrlDTO;
import com.shorturl.entity.UrlDO;
import com.shorturl.mapper.UrlMapper;
import com.shorturl.service.UrlService;
import com.shorturl.util.DateUtil;
import com.shorturl.util.ShortUrlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlMapper urlMapper;
    @Value("${server.servlet.context-path}")
    private String contextPath;

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
        String id = ShortUrlGenerator.generateShortUrlId(longUrl, 6);
        UrlDO urlDO = urlMapper.selectById(id);
        // if it exists, return
        if (urlDO != null && !StringUtils.isEmpty(urlDO.getShortUrl())) {
            return urlDO.getShortUrl();
        }

        urlDO = new UrlDO();
        urlDO.setId(id);
        urlDO.setLongUrl(longUrl);
        // shortUrl = host + context-path + /r/ +id
        urlDO.setShortUrl(urlDTO.getHost() + contextPath + "/r/" + id);
        Date nowDate = new Date();
        urlDO.setCreatedDate(nowDate);
        // valid day default 30
        int validDays = urlDTO.getValidDays() == 0 ? 30 : urlDTO.getValidDays();
        urlDO.setExpiresDate(DateUtil.addDayOfYear(nowDate, validDays));
        urlMapper.save(urlDO);
        return urlDO.getShortUrl();
    }

    @Override
    public void clearExpiredData() {
        urlMapper.clearExpiredData();
    }

    private String encodeUrl(String url) {
        try {
            if (url.indexOf("?") > -1) {
                String host = url.substring(0, url.indexOf("?"));
                String param = url.substring(url.indexOf("?") + 1, url.length());
                url = host + "?" + URLEncoder.encode(param, "UTF-8");
            }
        } catch (Exception e) {
            log.error("url encode error : {}", e);
        }
        return url;
    }

    private String decodeUrl(String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (Exception e) {
            log.error("url decode error : {}", e);
        }
        return url;
    }
}
