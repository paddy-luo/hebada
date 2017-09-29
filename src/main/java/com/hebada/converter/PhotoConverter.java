package com.hebada.converter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hebada.domain.Photo;
import com.hebada.utils.ImageUtils;
import com.hebada.web.request.PhotoListRequest;
import com.hebada.web.request.PhotoRequest;
import com.hebada.web.response.PageResponse;
import com.hebada.web.response.PhotoResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by paddy.luo on 2017/9/19.
 */
@Service
public class PhotoConverter {

    public List<Photo> convertToPhotoList(PhotoListRequest request) {
        //todo: 这边获取的上传图片以大图的数量为基准
        final Date now = new Date();
        List<Photo> photoList = Lists.newArrayList();
        for (int i = 0; i < request.getBigImageUrl().size(); i++) {
            Photo photo = new Photo();
            photo.setName(request.getName() + "_" + ImageUtils.getRandomString(5));
            photo.setProductId(request.getProductId());
            photo.setCatalogId(request.getCatalogId());
            photo.setBigImageUrl(request.getBigImageUrl().get(i));
            photo.setSmallImageUrl(request.getSmallImageUrl().get(i));
            photo.setDescription(request.getDescription());
            photo.setCreatedTime(now);
            photo.setUpdateTime(now);
            photoList.add(photo);
        }
        return photoList;
    }

    public Photo convertToPhoto(PhotoRequest request) {
        Photo photo = new Photo();
        photo.setName(request.getName());
        photo.setDescription(request.getDescription());
        photo.setProductId(request.getProductId());
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
        photoDomain.setProductId(photo.getProductId());
        photoDomain.setCatalogId(photo.getCatalogId());
        photoDomain.setBigImageUrl(photo.getBigImageUrl());
        photoDomain.setSmallImageUrl(photo.getSmallImageUrl());
        photoDomain.setDescription(photo.getDescription());
        photoDomain.setUpdateTime(new Date());
        return photoDomain;
    }

    public PageResponse<PhotoResponse> convertToPagePhotoResponse(Page<Photo> photoPage, int currentPage, int pageSize) {
        PageResponse<PhotoResponse> pageResponse = new PageResponse<PhotoResponse>();
        pageResponse.setTotal(photoPage.getTotalElements());
        pageResponse.setTotalPage(photoPage.getTotalPages());
        pageResponse.setCurrentPage(currentPage);
        pageResponse.setPageSize(pageSize);
        List<Photo> content = photoPage.getContent();
        if (CollectionUtils.isEmpty(content)) return pageResponse;
        for (Photo photo : content) {
            pageResponse.getContent().add(convertToPhotoResponse(photo));
        }
        return pageResponse;
    }

    public PhotoResponse convertToPhotoResponse(Photo photo) {
        PhotoResponse response = new PhotoResponse();
        response.setId(photo.getId());
        response.setName(photo.getName());
        response.setDescription(photo.getDescription());
        response.setBigImageUrl(photo.getBigImageUrl());
        response.setSmallImageUrl(photo.getSmallImageUrl());
        return response;
    }

    public List<Map<String, String>> convertToProductImageUrls(List<Photo> photos) {
        if (CollectionUtils.isEmpty(photos)) return null;
        List<Map<String, String>> imageUrls = Lists.newArrayList();
        for (Photo photo : photos) {
            Map<String, String> imageMap = Maps.newHashMap();
            imageMap.put("bigImageUrl", photo.getBigImageUrl());
            imageMap.put("smallImageUrl", photo.getSmallImageUrl());
            imageUrls.add(imageMap);
        }
        return imageUrls;
    }

    public Map<Long, Photo> convertToPhotoMap(List<Photo> photoList) {
        if (CollectionUtils.isEmpty(photoList)) return null;
        Map<Long, Photo> photoMap = Maps.newHashMap();
        for (Photo photo : photoList) {
            if (photoMap.get(photo.getProductId()) == null)
                photoMap.put(photo.getProductId(), photo);
        }
        return photoMap;
    }
}
