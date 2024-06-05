package com.processing;

import com.processing.domain.WeiboContent;
import com.processing.service.BaiduAiService;
import com.processing.service.WeiboContentService;
import com.processing.service.impl.BaiduAiServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chen
 * @createDate 2024/6/5 10:19
 */

@SpringBootTest
public class DataProcessingApplicationTests {

    @Resource
    private WeiboContentService weiboContentService;

    @Resource
    private BaiduAiService baiduAiService;



    @Test
    void demo11() {
        WeiboContent content = weiboContentService.getById(1);
        System.out.println(content.getContent());
    }

    @Test
    void demo22() {
        baiduAiService.optimizeParagraph("小米做了保时捷平替\n" +
                "极氪做了保时捷平替\n" +
                "问界同样做了保时捷平替\n" +
                "理想也做了保时捷平替\n" +
                "\n" +
                "所以保时捷现在这个局面很正常。\n" +
                "苹果牛逼吧 ，在咋们这不也被米华ov各种干。。。\n" +
                "\n" +
                "我劝保时捷，换个赛道吧 \u200B");

    }
}
