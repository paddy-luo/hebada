package com.hebada.service;

import com.hebada.domain.Catalog;
import com.hebada.repository.CatalogJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by paddy.luo on 2017/9/4.
 */
@Service
public class CatalogService {

    @Autowired
    private CatalogJpaRepository catalogJpaRepository;

    @Transactional
    public boolean saveOrUpdate(Catalog catalog) {
        if (catalog.getId() <= 0) {
            catalogJpaRepository.save(catalog);
            return true;
        }
        Catalog catalogDomain = catalogJpaRepository.findOne(catalog.getId());
        catalogDomain.setChineseName(catalog.getEnglishName());
        catalogDomain.setEnglishName(catalog.getEnglishName());
        catalogDomain.setParentId(catalog.getParentId());
        catalogDomain.setUpdateTime(catalog.getUpdateTime());
        catalogJpaRepository.save(catalogDomain);
        return true;
    }

    @Transactional
    public void delete(long id) {
        catalogJpaRepository.delete(id);
    }

    public Catalog get(long id) {
        return catalogJpaRepository.findOne(id);
    }

    public List<Catalog> findAll() {
        return catalogJpaRepository.findAll();
    }
}
