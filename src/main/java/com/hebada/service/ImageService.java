package com.hebada.service;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Created by paddy on 2017/9/7.
 */
@Service
public class ImageService {

    public List<String> upload(Map<String, MultipartFile> fileMap, String path) {
        List<String> filesPath = Lists.newArrayList();
        if (fileMap == null || fileMap.size() == 0) return filesPath;
        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File createFile(String path, String contentType) {
        String fileDirPath = path + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File dir = new File(fileDirPath);
        String filePath = fileDirPath + getRandomString(5) + "." + contentType;
        if (!dir.exists()) {
            dir.mkdirs();
            return new File(filePath);
        }
        File file = new File(filePath);
        while (file.exists()) {
            file = new File(fileDirPath + getRandomString(5) + "." + contentType);
        }
        return file;
    }

    public String getRandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

}
