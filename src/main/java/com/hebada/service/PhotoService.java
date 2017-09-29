package com.hebada.service;

import com.hebada.converter.PhotoConverter;
import com.hebada.domain.Photo;
import com.hebada.repository.PhotoJpaRepository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by paddy.luo on 2017/9/19.
 */
@Service
public class PhotoService {

    @Autowired
    private PhotoJpaRepository photoJpaRepository;
    @Autowired
    private PhotoConverter photoConverter;

    public Photo get(long id) {
        return photoJpaRepository.findOne(id);
    }

    @Transactional
    public void save(List<Photo> photoList) {
        if (CollectionUtils.isEmpty(photoList)) return;
        photoJpaRepository.save(photoList);
    }

    @Transactional
    public void update(Photo photo) {
        photoJpaRepository.save(photoConverter.convertToPhoto(this.get(photo.getId()), photo));
    }

    @Transactional
    public void delete(long id) {
        photoJpaRepository.delete(id);
    }

    public Page<Photo> findPhotoListByCatalogId(long catalogId, int currentPage, int pageSize) {
        return photoJpaRepository.findByCatalogId(catalogId, new PageRequest(currentPage, pageSize));
    }

    public List<Photo> findPhotoListByProductId(long productId) {
        return photoJpaRepository.findByProductIdOrderByIdAsc(productId);
    }

    public List<Photo> findPhotoListByProductIds(List<Long> productIds) {
        if (CollectionUtils.isEmpty(productIds)) return null;
        return photoJpaRepository.findAll(where(null, productIds));
    }

    public Page<Photo> findPhotoList(String name, int currentPage, int pageSize) {
        return photoJpaRepository.findAll(where(name, null), new PageRequest(currentPage, pageSize));
    }

    private Specification<Photo> where(final String name, final List<Long> productIds) {
        return new Specification<Photo>() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                if (StringUtils.hasLength(name))
                    cb.like(root.get("name"), "%" + name + "%");
                if (CollectionUtils.isNotEmpty(productIds))
                    cb.in(root.get("productId").in(productIds));
                query.orderBy(cb.desc(root.get("id")));
                return null;
            }
        };
    }
}
