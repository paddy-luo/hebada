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
  public void update(Catalog catalog) {
    catalogJpaRepository.save(catalogConverter.convertToCatalog(catalog, this.get(catalog.getId())));
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
