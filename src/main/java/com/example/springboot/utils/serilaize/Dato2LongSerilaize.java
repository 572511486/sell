package com.example.springboot.utils.serilaize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @filename:       Dato2LongSerilaize
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月07日21:57
 * @description:   
 *       日期转数字
 *       JsonSerialize注解,主要应用于数据转换,该注解作用在该属性的getter()方法上。
 */
public class Dato2LongSerilaize extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeNumber(date.getTime() / 1000);
	}
}
