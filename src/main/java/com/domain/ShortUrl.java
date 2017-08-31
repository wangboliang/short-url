package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 短链和长链映射关系
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Getter
@Setter
@ToString
public class ShortUrl {

    /**
     * 长链
     */
    private String longUrl;

    /**
     * 短链编码
     */
    private String shortCode;

    /**
     * 短链
     */
    private String shortUrl;

    /**
     * 点击数
     */
    private int  clicks;

}
