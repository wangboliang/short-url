package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 返回信息
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Getter
@Setter
@ToString
public class BaseResponse {

    private String returnCode;

    private String returnMessage;

    private String dataInfo;

}
