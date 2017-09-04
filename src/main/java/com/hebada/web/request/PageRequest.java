package com.hebada.web.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by paddy on 2017/9/3.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PageRequest {

  protected static final int DEFAULT_PAGE_NUM = 1;
  protected static final int DEFAULT_PAGE_SIZE = 10;

  @XmlElement(name = "pageNumber")
  protected int pageNumber;
  @XmlElement(name = "pageSize")
  protected int pageSize;

  public int getPageNumber() {
    if (this.pageNumber <= 0) return DEFAULT_PAGE_NUM;
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    if (this.pageSize <= 0) return DEFAULT_PAGE_SIZE;
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
