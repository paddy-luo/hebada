package com.hebada.web.controller;

import com.hebada.entity.HttpMethod;
import com.hebada.entity.URLs;
import com.hebada.web.response.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

/**
 * Created by paddy on 2017/9/7.
 */
@RequestMapping(value = URLs.IMAGE)
@Api(value = "image api", description = "image operation", basePath = URLs.IMAGE)
@RestController
public class ImageController {

    @RequestMapping(value = URLs.IMAGE_UPLOAD, method = RequestMethod.POST)
    @ApiOperation(value = "upload", notes = "image upload", httpMethod = HttpMethod.POST)
    public AjaxResponse upload(MultipartHttpServletRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
    }

}
