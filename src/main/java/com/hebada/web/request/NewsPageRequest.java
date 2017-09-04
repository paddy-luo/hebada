package com.hebada.web.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hebada.enumeration.NewsType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by paddy on 2017/9/3.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsPageRequest extends PageRequest {

  @XmlElement(name = "title", required = false)
  private String title;
  @XmlElement(name = "newsType", required = false)
  private NewsType newsType;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public NewsType getNewsType() {
    return newsType;
  }

  public void setNewsType(NewsType newsType) {
    this.newsType = newsType;
  }
}
