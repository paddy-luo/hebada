package com.hebada.service;

import com.hebada.converter.PhotoConverter;
import com.hebada.domain.Photo;
import com.hebada.repository.PhotoJpaRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        return photoJpaRepository.getOne(id);
    }

    @Transactional
    public boolean save(Photo photo) {
        photoJpaRepository.save(photo);
        if (photo.getId() > 0) return true;
        return false;
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
}
