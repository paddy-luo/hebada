package com.hebada.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paddy on 2017/9/14.
 */
public class ImageUtils {

    private static final String IMAGE_REGEX = "<img.*src=(.*?)[^>]*?>";
    private static final String IMAGE_SRC_REGEX = "src=\"?(.*?)(\"|>|\\s+)";

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
