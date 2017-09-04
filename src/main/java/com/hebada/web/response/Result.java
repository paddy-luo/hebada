package com.hebada.web.response;

/**
 * Created by paddy on 2017/9/3.
 */
public class Result<T> {

  private int code;
  private String description;
  private T data;

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

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
