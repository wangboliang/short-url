package com.shorturl;


import com.shorturl.util.ShortUrlGenerator;
import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.env.StandardEnvironment;

@Slf4j
public class AppTest {

    public static void main(String[] args) {
        String key = "https://www.baidu.com/";
        String md5 = ShortUrlGenerator.getMD5Encrypted(key);
        log.info("MD5 is : {}", md5);

        String shortCode = ShortUrlGenerator.generateShortUrlId(key, 6);
        log.info("shortCode is : {}", shortCode);

        // wangboliang md5
        System.setProperty("jasypt.encryptor.password", "254e3c056968e7a89830d409d8b4ebe0");
        StringEncryptor encryptor = new DefaultLazyEncryptor(new StandardEnvironment());
        log.info("encrypt : {}", encryptor.encrypt("root"));
        log.info("decrypt : {}", encryptor.decrypt(encryptor.encrypt("root")));
    }
}
