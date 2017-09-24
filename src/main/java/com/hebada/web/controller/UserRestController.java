package com.hebada.web.controller;

import com.hebada.domain.User;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.UserService;
import com.hebada.web.request.UserRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy on 2017/9/6.
 */
@RestController
@RequestMapping(value = URLs.USER)
@Api(basePath = URLs.USER, value = "UserRestController api", description = "User api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserRestController {

    @Inject
    private UserService userService;

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", httpMethod = HttpMethod.POST, notes = "save or update user info")
    public AjaxResponse saveOrUpdate(@RequestBody UserRequest request) {
        boolean saveOrUpdate = userService.saveOrUpdate(new User(request.getName(), request.getPassword()));
        if (saveOrUpdate) return AjaxResponse.ok();
        else return AjaxResponse.error().withDescription("操作失败");
    }

    @RequestMapping(value = URLs.USER_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", httpMethod = HttpMethod.DELETE, notes = "delete user")
    public AjaxResponse delete(@PathVariable long id) {
        userService.delete(id);
        return AjaxResponse.ok();
    }
}
