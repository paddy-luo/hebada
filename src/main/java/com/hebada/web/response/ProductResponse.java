package com.hebada.web.response;

/**
 * Created by paddy on 2017/9/16.
 */
public class ProductResponse extends PhotoResponse {

    private String content;
    private boolean recommended;

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

