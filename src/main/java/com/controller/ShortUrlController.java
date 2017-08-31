package com.controller;

import com.domain.BaseResponse;
import com.domain.ShortUrl;
import com.service.ShortUrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 短链生成控制器
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@RestController
@Slf4j
@AllArgsConstructor
public class ShortUrlController {

    private ShortUrlService shortUrlService;

    /**
     * 生成短链
     *
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateShortUrl", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse generateShortUrl(@RequestBody ShortUrl vo, HttpServletRequest request) {
        return shortUrlService.generateShortUrl(vo, request);
    }

    /**
     * 短链重定向到长链
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String shortUrl = requestURL.substring(1, requestURL.length());
        //查询长链
        String longUrl = shortUrlService.queryLongUrl(shortUrl);
        if (null == longUrl || "".equals(longUrl)) {
            //获取域名
            String host = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("/").toString();
            //跳转404
            longUrl = host + "404";
        }
        response.sendRedirect(longUrl);
    }

    /**
     * 错误界面跳转
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("404")
    public BaseResponse error(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setReturnCode("404");
        baseResponse.setReturnMessage("地址不存在");
        log.info("地址不存在");
        return baseResponse;
    }

}
