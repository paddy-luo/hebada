package com.hebada.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by paddy on 2017/9/3.
 */
@MappedSuperclass
public class CreateOrUpdateDomain {

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
