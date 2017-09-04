package com.hebada.web.controller;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@RestController
@RequestMapping(value = "/menu")
@Api(value = "menu api", basePath = "/menu")
public class MenuRestController {
}
