package com.hebada.service;

import com.hebada.convertor.MenuItemConverter;
import com.hebada.domain.MenuItem;
import com.hebada.repository.MenuJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Service
public class MenuService {

    @Autowired
    private MenuJpaRepository menuJpaRepository;
    @Autowired
    private MenuItemConverter menuItemConverter;

    public List<MenuItem> getMenuList() {
        List<MenuItem> menuItemsAll = menuJpaRepository.findAll();
        List<MenuItem> parentItems = menuItemConverter.filterToParent(menuItemsAll);
        for (MenuItem parentItem : parentItems) {
            for (MenuItem item : menuItemsAll) {
                if (item.getParentId() == null) continue;
                if (Long.compare(parentItem.getId(), item.getParentId()) == 0)
                    parentItem.getChildren().add(item);
            }
        }
        return parentItems;
    }

}
