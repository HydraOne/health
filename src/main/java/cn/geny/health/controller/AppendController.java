package cn.geny.health.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/3/9 16:59
 */
@RestController
@RequestMapping("/demo")
public class AppendController {

    @PostMapping("/test")
    public String test(@RequestBody JSONObject jsonObject) {
        String jsonString = "{\n" +
                "    \"code\": 20000,\n" +
                "    \"data\": {\n" +
                "        \"deptNum\": \"string\",\n" +
                "        \"displayName\": \"string\",\n" +
                "        \"workNum\": \"string\",\n" +
                "        \"expireFreshTime\": 0,\n" +
                "        \"expireMinite\": 0,\n" +
                "        \"l\": \"string\",\n" +
                "        \"email\": \"string\",\n" +
                "        \"deptName\": \"string\",\n" +
                "        \"mobile\": \"string\",\n" +
                "        \"userName\": \"system\",\n" +
                "        \"deptRoot\": \"string\"\n" +
                "    },\n" +
                "    \"message\": \"调用成功\"\n" +
                "}";
        JSONObject jsonObject1 = JSON.parseObject(jsonString);

        return jsonObject1.toString();
    }
}