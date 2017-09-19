package com.hebada.converter;

import com.hebada.domain.Photo;
import com.hebada.web.request.PhotoRequest;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * Created by paddy.luo on 2017/9/19.
 */
@Service
public class PhotoConverter {

    public Photo convertToPhoto(PhotoRequest request) {
        Photo photo = new Photo();
        photo.setName(request.getName());
        photo.setDescription(request.getDescription());
        photo.setCatalogId(request.getCatalogId());
        photo.setSmallImageUrl(request.getSmallImageUrl());
        photo.setBigImageUrl(request.getBigImageUrl());
        Date now = new Date();
        photo.setCreatedTime(now);
        photo.setUpdateTime(now);
        return photo;
    }

    public Photo convertToPhoto(Photo photoDomain, Photo photo) {
        photoDomain.setName(photo.getName());
        photoDomain.setCatalogId(photo.getCatalogId());
        photoDomain.setBigImageUrl(photo.getBigImageUrl());
        photoDomain.setSmallImageUrl(photo.getSmallImageUrl());
        photoDomain.setDescription(photo.getDescription());
        photoDomain.setUpdateTime(new Date());
        return photoDomain;
    }
}
