package cn.geny.health;

import cn.geny.health.po.Account;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/24 14:02
 */
public class TestReflectCase {

    @Test
    public void testReflect() throws ClassNotFoundException {
        Class check = new Account().getClass();
        Field[] declaredFields = check.getDeclaredFields();
        for (Field field:declaredFields) {
            System.out.println(field.getName());
        }
        Method[] declaredMethods = check.getDeclaredMethods();
    }

    @Test
    public void testReflectCase(){
        System.out.println(CheckType.Group);
    }


    @Test
    public void testCase(){
        long tmp = 2565295058157157651L;
    }

}
