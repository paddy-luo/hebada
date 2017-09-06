package com.hebada.web.controller;

import com.hebada.domain.MenuItem;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.MenuService;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@RestController
@RequestMapping(value = URLs.MENU)
@Api(value = "menu api", basePath = URLs.MENU, description = "menu operation")
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = URLs.MENU_LIST, method = RequestMethod.GET)
    @ApiOperation(value = "getMenuList", httpMethod = HttpMethod.GET, notes = "menu list")
    public AjaxResponse getMenuList() {
        List<MenuItem> menuList = menuService.getMenuList();
        return AjaxResponse.ok().withData(menuList);
    }
}
