package com.hebada.web.controller;

import com.hebada.entity.URLs;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy on 2017/9/16.
 */
@RestController
public class IndexController {

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "hebada rest api is running successfully";
    }
}
