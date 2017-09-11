package com.hebada.service;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by paddy on 2017/9/7.
 */
@Service
public class ImageService {

    private static final String filePathPrefix = "images";
    @Value("${image.file.maxSize:1}")
    private int imageFileSize;

    public List<String> upload(Map<String, MultipartFile> fileMap, String contextPath) {
        List<String> filesPath = Lists.newArrayList();
        if (fileMap == null || fileMap.size() == 0) return filesPath;
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            try {
                String filePath = createFile(file.getInputStream(), contextPath, file.getOriginalFilename());
                filesPath.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesPath;
    }

    private String createFile(InputStream source, String contextPath, String fileName) throws IOException {
        Calendar calendar = Calendar.getInstance();
        String fileDir = filePathPrefix + File.separator + calendar.get(Calendar.YEAR) + File.separator +
            (calendar.get(Calendar.MONTH) + 1) + File.separator + calendar.get(Calendar.DATE) + File.separator;
        String fileDirPath = contextPath + fileDir;
        File dir = new File(fileDirPath);
        if (!dir.exists())
            dir.mkdirs();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileSaveName = getRandomString(5) + fileType;
        String filePath = fileDirPath + fileSaveName;
        File destination = new File(filePath);
        while (destination.exists()) {
            filePath = fileDirPath + getRandomString(5) + fileType;
            destination = new File(filePath);
        }
        FileUtils.copyInputStreamToFile(source, destination);
        // 返回相对路径
        return fileDir + fileSaveName;
    }

    private String getRandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

}
