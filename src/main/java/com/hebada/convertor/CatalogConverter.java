package com.hebada.convertor;

import com.google.common.collect.Lists;
import com.hebada.domain.Catalog;
import com.hebada.web.request.CatalogRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by paddy on 2017/9/10.
 */
@Service
public class CatalogConverter {

    public Catalog convertToCatalog(CatalogRequest request) {
        Catalog catalog = new Catalog();
        catalog.setId(request.getId());
        catalog.setChineseName(request.getChineseName());
        catalog.setEnglishName(request.getEnglishName());
        catalog.setParentId(request.getParentId());
        final Date now = new Date();
        if (request.getId() <= 0) {
            catalog.setCreatedTime(now);
            catalog.setUpdateTime(now);
        } else {
            catalog.setUpdateTime(now);
        }
        return catalog;
    }


    public List<Catalog> convertToCatalogList(List<Catalog> result) {
        List<Catalog> catalogList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(result)) return catalogList;
        return getChildrenCatalog(result, null);
    }

    private List<Catalog> getChildrenCatalog(List<Catalog> catalogList, Long parentId) {
        List<Catalog> childrenCatalogList = Lists.newArrayList();
        for (Catalog catalog : catalogList) {
            if (catalog.getParentId() == parentId)
                childrenCatalogList.add(catalog);
        }
        if (CollectionUtils.isEmpty(childrenCatalogList)) return childrenCatalogList;
        for (Catalog child : childrenCatalogList) {
            child.getChildren().addAll(getChildrenCatalog(catalogList, child.getId()));
        }
        return childrenCatalogList;
    }
}