package com.shorturl;


import com.shorturl.util.ShortUrlGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppTest {

    public static void main(String[] args) {
        String key = "https://www.baidu.com/";
        String md5 = ShortUrlGenerator.getMD5Encrypted(key);
        log.info("MD5 is : {}", md5);

        String shortCode = ShortUrlGenerator.generateShortUrlId(key, 6);
        log.info("shortCode is : {}", shortCode);
    }
}
