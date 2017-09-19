package com.hebada.web.request;

/**
 * Created by paddy on 2017/9/10.
 */
public class ProductRequest extends PhotoRequest {

    private String content;
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
