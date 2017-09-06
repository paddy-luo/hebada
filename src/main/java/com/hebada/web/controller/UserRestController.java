package com.hebada.web.controller;

import com.hebada.domain.User;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.UserService;
import com.hebada.web.request.UserRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(basePath = URLs.USER, value = "UserRestController api", description = "User operation")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = URLs.USER_LOGIN, method = RequestMethod.POST)
    @ApiOperation(value = "login", httpMethod = HttpMethod.POST, notes = "login")
    public AjaxResponse login(@RequestBody UserRequest request) {
        boolean hasUser = userService.findByNameAndPassword(request.getName(), request.getPassword());
        if (hasUser) return AjaxResponse.ok();
        else return AjaxResponse.error().withDescription("账户和密码错误");
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "saveOrUpdate", httpMethod = HttpMethod.POST, notes = "saveOrUpdate or update user info")
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
