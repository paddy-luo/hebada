package com.hebada.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by paddy on 2017/9/3.
 */
@Entity
@Table(name = "t_menu")
public class MenuItem extends AbstractEntity {

  @Id
  @GeneratedValue
  private long id;

  @Column(name = "chinese_name")
  private String chineseName;

  @Column(name = "english_name")
  private String englishName;

  @Column(name = "icon_url")
  private String iconUrl;

  @Column(name = "parent_id")
  private long parentId;

  @Transient
  private final List<MenuItem> children = new ArrayList<MenuItem>();

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getChineseName() {
    return chineseName;
  }

  public void setChineseName(String chineseName) {
    this.chineseName = chineseName;
  }

  public String getEnglishName() {
    return englishName;
  }

  public void setEnglishName(String englishName) {
    this.englishName = englishName;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }

  public List<MenuItem> getChildren() {
    return children;
  }

}
