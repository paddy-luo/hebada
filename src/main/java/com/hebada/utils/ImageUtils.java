package com.hebada.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paddy on 2017/9/14.
 */
public class ImageUtils {

    private static final String filePathPrefix = "images";
    private static final String IMAGE_REGEX = "<img.*src=(.*?)[^>]*?>";
    private static final String IMAGE_SRC_REGEX = "src=\"?(.*?)(\"|>|\\s+)";

    public static List<String> upload(Map<String, MultipartFile> fileMap, String contextPath) {
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

    private static String createFile(InputStream source, String contextPath, String fileName) throws IOException {
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

    public static String getRandomString(int length) {
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    public static List<String> getImageUrlsFromHtml(String htmlStr) {
        StringBuffer image = new StringBuffer();
        List<String> imageList = Lists.newArrayList();
        Pattern pattern = Pattern.compile(IMAGE_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        while (matcher.find()) {
            image = image.append("," + matcher.group());
            Matcher m = Pattern.compile(IMAGE_SRC_REGEX).matcher(image);
            while (m.find()) {
                imageList.add(m.group(1));
            }
        }
        return imageList;
    }

    public static String getImageUrlFirstFromHtml(String htmlStr) {
        List<String> imageUrls = getImageUrlsFromHtml(htmlStr);
        if (CollectionUtils.isEmpty(imageUrls)) return null;
        return imageUrls.get(0);
    }
}
