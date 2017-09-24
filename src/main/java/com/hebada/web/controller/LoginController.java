package com.hebada.web.controller;

import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.UserService;
import com.hebada.web.WebConfig;
import com.hebada.web.request.UserRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by paddy on 2017/9/8.
 */
@Controller
@RequestMapping(value = URLs.USER)
@Api(value = "login api", basePath = URLs.USER, description = "login api")
public class LoginController {

    @Inject
    private UserService userService;

    //todo: 登录验证失败
    @RequestMapping(value = URLs.USER_LOGIN, method = RequestMethod.POST)
    @ApiOperation(value = "login", httpMethod = HttpMethod.POST, notes = "login")
    @ResponseBody
    public AjaxResponse login(@RequestBody UserRequest request, HttpServletResponse response) {
        boolean hasUser = userService.findByNameAndPassword(request.getName(), request.getPassword());
        if (hasUser) {
            response.addCookie(new Cookie(WebConfig.LOGIN_USER, request.getName()));
            return AjaxResponse.ok();
        } else return AjaxResponse.error().withDescription("账户和密码错误");
    }

    @RequestMapping(value = URLs.USER_LOGOUT, method = RequestMethod.GET)
    @ApiOperation(value = "logout", httpMethod = HttpMethod.POST, notes = "logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return URLs.USER_RETURN_LOGIN;
    }
}
