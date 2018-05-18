package com.app.framework.core.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangyijun on 2018/4/13.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @ApiModelProperty(value = "响应编码")
    public int code;
    @ApiModelProperty(value = "响应消息")
    public String msg;
    @ApiModelProperty(value = "响应数据")
    public Object data;

    public static Response success() {
        return buildResponse(Status.SUCCESS.code(), "success", null);
    }

    public static Response success(Object data) {
        return buildResponse(Status.SUCCESS.code(), "success", data);
    }

    public static Response error() {
        return buildResponse(1, "fail", null);
    }

    public static Response error(String msg) {
        return buildResponse(1, msg, null);
    }

    public static Response error(int code, String msg) {
        return buildResponse(code, msg, null);
    }

    private static Response buildResponse(int code, String msg, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }
}
