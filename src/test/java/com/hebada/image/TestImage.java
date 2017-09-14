package com.hebada.image;

import com.hebada.utils.ImageUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by paddy on 2017/9/8.
 */
public class TestImage {

    @Test
    public void testImage() {
        String imageHtml = "<p style='text-align: center;'><span class='marker'><strong>1104测试2</strong></span></p>"
            + "<h2 style='font-style:italic;'>这是测是使用</h2>"
            + "<p><img alt='' src='http://10.10.10.242:82/yaodu/contracts/1478225023392_9c363621-5c13-4308-a77e-3206cb929153.jpg' style='height:220px; width:391px' /></p>"
            + "<p>&nbsp;</p>"
            + "<p>测试图片截取的</p>"
            + "<p><img alt='' src='http://10.10.10.242:82/yaodu/contracts/1478225059568_a73665bc-0909-4045-9c6d-4e8b8b0190c8.jpg' style='height:220px; width:293px' /></p>"
            + "<p>&nbsp;</p>"
            + "<p>图片3</p>"
            + "<p><img alt='' src='http://10.10.10.242:82/yaodu/contracts/1478225079772_c668f171-d5e5-4e98-aac2-98477a11d972.jpg' style='height:200px; width:200px' /></p>"
            + "<p>&nbsp;</p>";
        List<String> imageUrls = ImageUtils.getImageUrlsFromHtml(imageHtml);
        Assert.assertNotNull(imageUrls);
    }
}
