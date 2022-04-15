package cn.geny.health.convert;

import cn.geny.health.CheckType;
import org.apache.commons.lang3.EnumUtils;
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
}