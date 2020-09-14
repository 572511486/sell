package com.example.springboot.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import com.example.springboot.domain.OrderDetail;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.form.OrderForm;
import java.util.ArrayList;
import java.util.List;

/**
 * @filename:       OrderForm2OrderDto
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月07日20:27
 * @description:   
 *        订单表单 转 OrderDto
 */
@Slf4j
public class OrderForm2OrderDto {
	/**
	 * @author 		   FZH
	 * @date 		   2020/9/7
	 * @parameter 	   [orderForm]
	 * @return 		   com.example.springboot.dto.OrderDto
	 * @description
	 *       订单表单 转 OrderDto
	 **/
    public static OrderDto convert(OrderForm orderForm) {
		OrderDto orderDto = new OrderDto();
		orderDto.setBuyerName(orderForm.getName());
		orderDto.setBuyerAddress(orderForm.getAddress());
		orderDto.setBuyerOpenid(orderForm.getOpenid());
		orderDto.setBuyerPhone(orderForm.getPhone());
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			Gson gson = new Gson();
			orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
			}.getType());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			log.error("【对象转换】 错误:string={}", orderForm.getItems());
		}
		orderDto.setOrderDetails(orderDetailList);
		return orderDto;
	}
}
