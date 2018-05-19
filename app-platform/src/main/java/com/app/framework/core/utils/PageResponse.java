package com.app.framework.core.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yangyijun on 2018/4/16.
 */
@Data
@NoArgsConstructor
public class PageResponse extends Response {
    private long total;

    public PageResponse(int code, String msg, long total, Object data) {
        this.code = code;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }

    public static PageResponse success(Object data, long total) {
        PageResponse response = new PageResponse(Status.SUCCESS.code(), Status.SUCCESS.msg(), total, data);
        return response;
    }
}
