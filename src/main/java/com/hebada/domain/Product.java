package com.hebada.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Entity(name = "t_product")
public class Product extends Photo {

    @Column(name = "content")
    private String content;

    @Column(name = "recommended")
    private boolean recommended;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}
