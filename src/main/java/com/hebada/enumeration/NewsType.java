package com.hebada.enumeration;

/**
 * Created by paddy on 2017/9/3.
 */
public enum NewsType {

  NEWS(1, "新闻"),
  BULLETIN(2, "公告");

  private int code;
  private String description;

  NewsType(int code, String description) {
    this.code = code;
    this.description = description;
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
}
