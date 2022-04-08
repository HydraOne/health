package cn.geny.health.utils;

import java.util.UUID;

/**
 * @author wangjiahao
 * @date 2022-01-15
 */
public class UUIDUtil {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
