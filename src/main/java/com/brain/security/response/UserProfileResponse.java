package com.brain.security.response;

/**
 *
 * @author firecode16
 */
public class UserProfileResponse {
    private String result;
    private String message;
    private Object data;

    public UserProfileResponse() {}

    public UserProfileResponse(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public UserProfileResponse(String result, Object data) {
        this.result = result;
        this.data = data;
    }

    public UserProfileResponse(String result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
