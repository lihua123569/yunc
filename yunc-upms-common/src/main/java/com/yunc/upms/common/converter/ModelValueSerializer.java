package com.yunc.upms.common.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class ModelValueSerializer implements ObjectSerializer {
    
	@Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
                      int features) throws IOException {
        Long value = (Long) object;
        String text = value + "";
        serializer.write(text);
    }

}