package com.hebada.web.response;

/**
 * Created by paddy on 2017/9/6.
 */
public class AjaxResponse {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;

    private int code;
    private String description;
    private Object data;

    public static AjaxResponse ok() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(SUCCESS);
        return ajaxResponse;
    }

    public static AjaxResponse error() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(ERROR);
        return ajaxResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AjaxResponse withData(Object data) {
        this.data = data;
        return this;
    }

    public AjaxResponse withDescription(String description) {
        this.description = description;
        return this;
    }
}
