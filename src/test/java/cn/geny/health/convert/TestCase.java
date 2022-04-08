package cn.geny.health.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void testConvert() throws JsonProcessingException {
        String json = "{\"maxFieldS\":1,\"minField\":2}";
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println("strT".replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase());
////        String json = "{\"test_first\":1,\"min_field\":2}";
//        ObjectMapper mapper=new ObjectMapper();
//        mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
//        String lowerCaseJson = mapper.writeValueAsString(jsonObject);
    }

//    public static String formatJSONString(JSONObject jsonObject) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.LOWER_CASE);
//        String jsonString = null;
//        try {
//            jsonString = mapper.writeValueAsString(jsonObject.toJSONString());
//        } catch (JsonProcessingException e) {
//            logger.error("JSON字符串格式化出错", e);
//        }
//        return jsonString;
//    }
//
//    public static String toUnderlineJSONString(Object object) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
////        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
////        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        mapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
//        String reqJson = mapper.writeValueAsString(object);
//        return reqJson;
//    }

//    public Map<String, Object> jsonToMap(String jsonString) throws IOException
//    {
//        ObjectMapper mapper=new ObjectMapper();
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//        Map<String, Object> map = mapper.readValue(jsonString,new TypeReference<Map<String, Object>>(){});
//        return convertMap(map);
//    }

//    public String mapKey(String key) {
//        return Character.toLowerCase(key.charAt(0)) + key.substring(1);
//    }
//
//    public Map<String, Object> convertMap(Map<String, Object> map) {
//        Map<String, Object> result = new HashMap<String, Object>();
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            result.put(mapKey(key), covertValue(value));
//        }
//        return result;
//    }
//
//    public List convertList(List<Object> list) {
//        List<Object> result = new ArrayList<Object>();
//        for (Object obj : list) {
//            result.add(covertValue(obj));
//        }
//        return result;
//    }
//
//    public Object covertValue(Object obj) {
//        if (obj instanceof Map) {
//            return convertMap((Map<String, Object>) obj);
//        } else if (obj instanceof List) {
//            return convertList((List<Object>) obj);
//        } else {
//            return obj;
//        }
//    }
}