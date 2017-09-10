package com.hebada.service;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by paddy on 2017/9/7.
 */
@Service
public class ImageService {

    @Value("${image.file.maxSize:1}")
    private int imageFileSize;

    public List<String> upload(Map<String, MultipartFile> fileMap, String path) {
        List<String> filesPath = Lists.newArrayList();
        if (fileMap == null || fileMap.size() == 0) return filesPath;
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            try {
                String filePath = createFile(file.getInputStream(), path, file.getContentType());
                filesPath.add(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesPath;
    }

    private String createFile(InputStream source, String path, String contentType) throws IOException {
        String fileDirPath = path + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File dir = new File(fileDirPath);
        String filePath = fileDirPath + getRandomString(5) + "." + contentType;
        if (!dir.exists())
            dir.mkdirs();
        File destination = new File(filePath);
        while (destination.exists()) {
            filePath = fileDirPath + getRandomString(5) + "." + contentType;
            destination = new File(filePath);
        }
        FileUtils.copyInputStreamToFile(source, destination);
        return filePath;
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
