package com.ezmall.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dengqf
 * Date: 14-6-17
 * Time: 下午11:39
 * To change this template use File | Settings | File Templates.
 */
public class MessageVo implements Serializable {

    private String id;

    private boolean success;//执行结果

    // 返回代码
    private String reCode;

    private String message;//返回信息

    private Object obj;//返回对象

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

