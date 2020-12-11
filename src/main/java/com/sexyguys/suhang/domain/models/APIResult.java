package com.sexyguys.suhang.domain.models;

public class APIResult {
    private int statusCode;
    private String bodyMsg;
    private Object output;
    private Object error;

    public APIResult(int statusCode, String bodyMsg, Object output, Object error) {
        this.statusCode = statusCode;
        this.bodyMsg = bodyMsg;
        this.output = output;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBodyMsg() {
        return bodyMsg;
    }

    public void setBodyMsg(String bodyMsg) {
        this.bodyMsg = bodyMsg;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
    