package com.hebada.entity;

/**
 * Created by paddy on 2017/9/8.
 */
public enum FileType {
    BMP("bmp"),
    GIF("gif"),
    JPEG("jpeg"),
    PNG("png");

    private String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
