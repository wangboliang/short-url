package com.service;

import com.domain.BaseResponse;
import com.domain.ShortUrl;
import com.utils.ShortUrlGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <p>
 * 短链生成业务层
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Slf4j
@Service
public class ShortUrlService {

    /**
     * 生成短链
     *
     * @param vo
     * @param request
     * @return
     */
    public BaseResponse generateShortUrl(ShortUrl vo, HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        log.info("短链生成请求参数：{}", vo);
        try {
            if (null == vo.getLongUrl() || "".equals(vo.getLongUrl())) {
                baseResponse.setReturnCode("400");
                baseResponse.setReturnMessage("长链不允许为空");
            } else {
                //长链进行url编码
                String longUrl = encodeUrl(vo.getLongUrl());
                log.info("UTF-8编码之后长链：{}", longUrl);

                //生成短链
                String shortCode = ShortUrlGenerator.shortUrl(longUrl, "wangboliang");

                StringBuffer requestURL = request.getRequestURL();
                //获取域名
                String host = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("/").toString();
                log.info("当前服务域名：{}", host);

                //组合短链
                String shortUrl = host + shortCode;

                //把短链与长链映射关系存入关系型数据库或noSql中
                //todo

                baseResponse.setReturnCode("200");
                baseResponse.setReturnMessage("成功");
                baseResponse.setDataInfo(shortUrl);
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        log.info("短链生成返回结果：{}", baseResponse);
        return baseResponse;
    }

    /**
     * 查询长链
     *
     * @param shortUrl
     * @return
     */
    public String queryLongUrl(String shortUrl) {
        String longUrl = "";
        try {
            if (null == shortUrl || "".equals(shortUrl)) {
                longUrl = "";
            } else {
                //根据短链从关系型数据库或缓存中获取长链
                //todo
                longUrl = "https://github.com/wangboliang";

                //url解码
                longUrl = URLDecoder.decode(longUrl, "UTF-8");
            }
        } catch (Exception e) {
            log.error("error", e);
        }
        return longUrl;
    }

    /**
     * url编码
     *
     * @param longUrl
     * @return
     * @throws Exception
     */
    protected String encodeUrl(String longUrl) throws Exception {
        String result;
        if (longUrl.indexOf("?") > -1) {
            String host = longUrl.substring(0, longUrl.indexOf("?"));
            String param = longUrl.substring(longUrl.indexOf("?") + 1, longUrl.length());
            result = host + "?" + URLEncoder.encode(param, "UTF-8");
        } else {
            result = longUrl;
        }
        return result;
    }

}
