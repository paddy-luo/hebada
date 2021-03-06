package com.hebada.converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hebada.domain.Catalog;
import com.hebada.entity.RouterTemplateName;
import com.hebada.web.request.CatalogRequest;
import com.hebada.web.response.CatalogResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * Created by paddy on 2017/9/10.
 */
@Service
public class CatalogConverter {

    public Catalog convertToCatalog(CatalogRequest request) {
        Catalog catalog = new Catalog();
        catalog.setChineseName(request.getChineseName());
        catalog.setEnglishName(request.getEnglishName());
        catalog.setParentId(request.getParentId());
        catalog.setTemplateName(RouterTemplateName.getRouterTemplate(request.getTemplateName()));
        final Date now = new Date();
        if (request.getId() <= 0) {
            catalog.setCreatedTime(now);
            catalog.setUpdateTime(now);
        } else {
            catalog.setUpdateTime(now);
        }
        return catalog;
    }

    public Catalog convertToCatalog(Catalog catalog, Catalog catalogDomain) {
        catalogDomain.setChineseName(catalog.getChineseName());
        catalogDomain.setEnglishName(catalog.getEnglishName());
        catalog.setTemplateName(catalogDomain.getTemplateName());
        catalogDomain.setParentId(catalog.getParentId());
        catalogDomain.setUpdateTime(new Date());
        return catalogDomain;
    }

    public CatalogResponse convertToCatalogResponse(Catalog catalog) {
        CatalogResponse response = new CatalogResponse();
        response.setId(catalog.getId());
        response.setChineseName(catalog.getChineseName());
        response.setEnglishName(catalog.getEnglishName());
        response.setTemplateName(catalog.getTemplateName().getName());
        if (catalog.getParentId() != null)
            response.setParentId(catalog.getParentId());
        if (CollectionUtils.isEmpty(catalog.getChildren())) return response;
        for (Catalog children : catalog.getChildren()) {
            response.getChildren().add(convertToCatalogResponse(children));
        }
        return response;
    }

    public Map<Long, CatalogResponse> convertToCatalogResponse(List<Catalog> result) {
        Map<Long, CatalogResponse> catalogResponseHashMap = Maps.newHashMap();
        if (CollectionUtils.isEmpty(result)) return catalogResponseHashMap;
        for (Catalog catalog : result) {
            catalogResponseHashMap.put(catalog.getId(), convertToCatalogResponse(catalog));
        }
        return catalogResponseHashMap;
    }

    public List<Catalog> convertToCatalogList(List<Catalog> result) {
        List<Catalog> catalogList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(result)) return catalogList;
        return getChildrenCatalog(result, 0L);
    }

    private List<Catalog> getChildrenCatalog(List<Catalog> catalogList, long parentId) {
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
