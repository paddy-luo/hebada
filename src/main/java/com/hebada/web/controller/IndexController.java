package com.hebada.web.controller;

import com.hebada.entity.URLs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by paddy on 2017/9/16.
 */
@Controller
public class IndexController {

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("name", "thyme is ok");
        return URLs.PAGE_INDEX;
    }
}
