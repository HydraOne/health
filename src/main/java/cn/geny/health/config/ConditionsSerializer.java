package cn.geny.health.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Map;

/**
 * TODO
 *
 * @author wangjiahao
 * @date 2022/4/7 12:26
 */
public class ConditionsSerializer extends StdDeserializer<Map> {

    public ConditionsSerializer() {
        this(null);
    }

    protected ConditionsSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        System.out.println(p.getText());;
        return null;
    }
}
