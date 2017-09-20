package com.hebada.service;

import com.hebada.converter.PhotoConverter;
import com.hebada.converter.ProductConverter;
import com.hebada.domain.Photo;
import com.hebada.domain.Product;
import com.hebada.repository.PhotoJpaRepository;
import com.hebada.repository.ProductJpaRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by paddy on 2017/9/10.
 */
@Service
public class ProductService {

    @Inject
    private ProductJpaRepository productJpaRepository;
    @Inject
    private PhotoJpaRepository photoJpaRepository;
    @Inject
    private ProductConverter productConverter;
    @Inject
    private PhotoConverter photoConverter;

    public Product get(long id) {
        return productJpaRepository.findOne(id);
    }

    @Transactional
    public void save(Product product, List<String> bigImageUrls, List<String> smallImageUrls) {
        productJpaRepository.save(product);
        List<Photo> photoList = photoConverter.convertToProductPhotoList(product.getId(),
            product.getCatalogId(), bigImageUrls, smallImageUrls);
        if (CollectionUtils.isEmpty(photoList)) return;
        photoJpaRepository.save(photoList);
    }

    @Transactional
    public void update(Product product) {
        productJpaRepository.save(productConverter.convertToProduct(product, this.get(product.getId())));
    }

    @Transactional
    public void delete(long id) {
        productJpaRepository.delete(id);
    }

    public Page<Product> findAllByCatalogId(long catalogId, int currentPage, int pageSize) {
        return productJpaRepository.findAllByCatalogId(catalogId, new PageRequest(currentPage, pageSize));
    }
}
