package cn.geny.health.convert;

import cn.geny.health.CheckType;
import org.apache.commons.lang3.EnumUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/7 10:38
 */
public class TestCase {


    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void testCheckType(){
        System.out.println(EnumUtils.isValidEnum(CheckType.class,null));
    }


    @Test
    public void testGet() throws Exception {
        String test = "<html><head></head><body><p><img src=\"cc02d6256c46506d7e1000c2c47127e7\"></p></body></html>";
        Document document = Jsoup.parse(test);
        Elements imgs = document.getElementsByTag("img");
        imgs.forEach(img->{
//            img.removeAttr("src");
            img.attr("src","demo"+img.attr("src"));
        });
        System.out.println(document.outerHtml());
    }
}