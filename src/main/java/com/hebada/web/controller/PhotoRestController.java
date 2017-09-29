package com.hebada.web.controller;

import com.hebada.converter.PhotoConverter;
import com.hebada.domain.Photo;
import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.service.PhotoService;
import com.hebada.utils.ImageUtils;
import com.hebada.web.request.PageRequest;
import com.hebada.web.request.PhotoListRequest;
import com.hebada.web.request.PhotoRequest;
import com.hebada.web.request.PhotoSearchRequest;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by paddy on 2017/9/7.
 */
@RequestMapping(value = URLs.PHOTO)
@Api(value = "photo api", description = "photo api", basePath = URLs.PHOTO)
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PhotoRestController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoConverter photoConverter;

    @RequestMapping(value = URLs.PHOTO_UPLOAD, method = RequestMethod.POST)
    @ApiOperation(value = "upload", notes = "image upload", httpMethod = HttpMethod.POST)
    public AjaxResponse upload(MultipartHttpServletRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        List<String> upload = ImageUtils.upload(fileMap, request.getServletContext().getRealPath(""));
        return AjaxResponse.ok().withData(upload);
    }

    @RequestMapping(value = URLs.PRODUCT_ID, method = RequestMethod.GET)
    @ApiOperation(value = "save", notes = "save image", httpMethod = HttpMethod.GET)
    public AjaxResponse get(@PathVariable long id) {
        return AjaxResponse.ok().withData(photoService.get(id));
    }

    @RequestMapping(value = URLs.DEFAULT, method = RequestMethod.POST)
    @ApiOperation(value = "save", notes = "save image", httpMethod = HttpMethod.POST)
    public AjaxResponse save(@Validated @RequestBody PhotoListRequest request) {
        photoService.save(photoConverter.convertToPhotoList(request));
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PHOTO_ID, method = RequestMethod.DELETE)
    @ApiOperation(value = "delete", notes = "delete image", httpMethod = HttpMethod.DELETE)
    public AjaxResponse delete(@PathVariable long id) {
        photoService.delete(id);
        return AjaxResponse.ok();
    }

    @RequestMapping(value = URLs.PHOTO_ID, method = RequestMethod.POST)
    @ApiOperation(value = "delete", notes = "delete image", httpMethod = HttpMethod.POST)
    public AjaxResponse update(@PathVariable long id, @Validated @RequestBody PhotoRequest request) {
        request.setId(id);
        photoService.update(photoConverter.convertToPhoto(request));
        return AjaxResponse.ok();
    }


    @RequestMapping(value = URLs.LIST, method = RequestMethod.POST)
    @ApiOperation(value = "photoList", notes = "photo list", httpMethod = HttpMethod.POST)
    public AjaxResponse getPhotoList(@RequestBody PageRequest<PhotoSearchRequest> request) {
        Page<Photo> photoPage = photoService.findPhotoList(request.getParams().getName(), request.getPageNumber(), request.getPageSize());
        
    }
}
