package com.ezmall.dto;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-8-1
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
public class JsonDto {
    /*默认100  ,error为错误  */
    private String result;

    private String message;



    private Object data;

    public JsonDto(String result, Object data) {
        this.result = result;
        this.data = data;
    }

    public JsonDto(String result, String message, Object data) {
        this.result = result;
        this.message = message;
         this.data = data;
    }

    public JsonDto() {
    }

    public JsonDto(Object data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
