package com.test;


import com.utils.ShortUrlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 测试类
 */
@Slf4j
public class AppTest {

    @Test
    public void testShortUrl() throws Exception {
        // 长连接
        String longUrl = "https://github.com/wangboliang";
        // 转换成的短链接后6位码
        String shortCode = ShortUrlGenerator.shortUrl(longUrl, "wangboliang");
        log.info("shortCode is :{}", shortCode);
    }

    @Test
    public void testMD5() throws Exception {
        String key = "wangboliang";
        String md5 = ShortUrlGenerator.getMD5Encrypted(key);
        log.info("MD5 is :{}", md5);
    }

}
