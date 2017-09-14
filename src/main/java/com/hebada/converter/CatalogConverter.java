package com.hebada.converter;

import com.google.common.collect.Lists;
import com.hebada.domain.Catalog;
import com.hebada.web.request.CatalogRequest;
import com.hebada.web.response.CatalogResponse;
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
        catalog.setChineseName(request.getChineseName());
        catalog.setEnglishName(request.getEnglishName());
        catalog.setParentId(request.getParentId());
        catalog.setTemplateName(request.getTemplateName());
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
        if (CollectionUtils.isEmpty(catalog.getChildren())) return response;
        for (Catalog children : catalog.getChildren()) {
            response.getChildren().add(convertToCatalogResponse(children));
        }
        return response;
    }

    public List<CatalogResponse> convertToCatalogResponse(List<Catalog> result) {
        List<CatalogResponse> catalogResponseList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(result)) return catalogResponseList;
        for (Catalog catalog : result) {
            catalogResponseList.add(convertToCatalogResponse(catalog));
        }
        return catalogResponseList;
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
