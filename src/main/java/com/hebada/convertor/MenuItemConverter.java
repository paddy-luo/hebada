package com.hebada.convertor;

import com.google.common.collect.Lists;
import com.hebada.domain.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Service
public class MenuItemConverter {

    //todo: 只支持获取二级菜单, 考虑使用lambar
    public List<MenuItem> filterToParent(List<MenuItem> menuItems) {
        if (CollectionUtils.isEmpty(menuItems)) return Lists.newArrayList();
        List<MenuItem> parentMenuItem = Lists.newArrayList();
        for (MenuItem item : menuItems) {
            if (item.getParentId() == null)
                parentMenuItem.add(item);
        }
        return parentMenuItem;
    }
}
