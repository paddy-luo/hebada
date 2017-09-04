package com.hebada.domain;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.sql.Date;

/**
 * Created by paddy on 2017/9/3.
 */
public class AbstractEntity {

  @Column(name = "create_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdTime;

  @Column(name = "update_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateTime;
}
