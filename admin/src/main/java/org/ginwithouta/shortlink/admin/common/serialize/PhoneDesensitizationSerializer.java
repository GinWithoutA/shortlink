package org.ginwithouta.shortlink.admin.common.serialize;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Package : org.ginwithouta.shortlink.admin.common.serialize
 * @Author : NONO Wang
 * @Date : 2023 - 11月 - 周三
 * @Desc : 手机号脱敏
 */
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {

    /**
     * 手机号脱敏
     * @param phone                 需要脱敏的字段
     * @param jsonGenerator         对象通用json构造器
     * @param serializerProvider    序列化器
     */
    @Override
    public void serialize(String phone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String phoneDesensitization = DesensitizedUtil.mobilePhone(phone);
        jsonGenerator.writeString(phoneDesensitization);
    }
}
