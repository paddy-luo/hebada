package com.hebada.web.request;

import org.apache.commons.codec.digest.DigestUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by paddy on 2017/9/6.
 */
public class UserRequest {

    private int id;
    @NotNull(message = "user name is null")
    private String name;
    @NotNull(message = "user password is null")
    private String password;
    private String originalPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return DigestUtils.md5Hex(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalPassword() {
        return DigestUtils.md5Hex(this.originalPassword);
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }
}
