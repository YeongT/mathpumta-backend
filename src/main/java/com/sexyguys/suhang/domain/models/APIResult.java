package com.sexyguys.suhang.domain.models;

public class APIResult {
    public int statusCode;
    public String bodyMsg;
    public Object output;
    public Object error;

    public APIResult(int statusCode, String bodyMsg, Object output, Object error) {
        this.statusCode = statusCode;
        this.bodyMsg = bodyMsg;
        this.output = output;
        this.error = error;
    }
}
    