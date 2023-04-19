package com.shorturl.domain;

public class Response<T> {

    private String code;

    private String msg;

    private T data;

    public Response(String code, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success() {
        return new Response(ReturnCodeEnum.SUCCESS.getCode(), null);
    }

    public static Response success(Object data) {
        return new Response(ReturnCodeEnum.SUCCESS.getCode(), data);
    }

    public static Response failed(String code, String msg) {
        return new Response(code, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum ReturnCodeEnum {
        SUCCESS("0", "成功"),
        FAIL("-1", "失败");

        private String code;
        private String message;

        ReturnCodeEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
