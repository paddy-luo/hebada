package com.hebada.service;

import com.hebada.convertor.MenuItemConverter;
import com.hebada.domain.MenuItem;
import com.hebada.repository.MenuJpaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    List<MenuItem> parentMenuItems = menuItemConverter.filterToParent(menuItemsAll);
    for (MenuItem menuItem : parentMenuItems) {
      for (MenuItem item : menuItemsAll) {
        if (menuItem.getId() == menuItem.getParentId())
          menuItem.getChildren().add(item);
      }
    }
    return parentMenuItems;
  }

}
