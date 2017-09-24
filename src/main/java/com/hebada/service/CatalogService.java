package com.hebada.service;

import com.hebada.converter.CatalogConverter;
import com.hebada.domain.Catalog;
import com.hebada.repository.CatalogJpaRepository;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Service
public class CatalogService {

    @Inject
    private CatalogJpaRepository catalogJpaRepository;
    @Inject
    private CatalogConverter catalogConverter;

    @Transactional
    public boolean save(Catalog catalog) {
        catalogJpaRepository.save(catalog);
        if (catalog.getId() > 0)
            return true;
        return false;
    }

    @Transactional
    public void update(long id, Catalog catalog) {
        Catalog catalogDomain = catalogJpaRepository.findOne(id);
        if (catalogDomain == null) return;
        catalogJpaRepository.save(catalogConverter.convertToCatalog(catalog, catalogDomain));
    }

    @Transactional
    public void delete(long id) {
        catalogJpaRepository.delete(id);
        catalogJpaRepository.deleteAllByParentId(id);
    }

    public Catalog get(long id) {
        Catalog catalog = catalogJpaRepository.findOne(id);
        catalog.getChildren().addAll(this.findAllByParentId(catalog.getId()));
        return catalog;
    }

    public List<Catalog> findAll() {
        return catalogJpaRepository.findAll();
    }

    public List<Catalog> findAllByParentId(long parentId) {
        return catalogJpaRepository.findAllByParentId(parentId);
    }

}
