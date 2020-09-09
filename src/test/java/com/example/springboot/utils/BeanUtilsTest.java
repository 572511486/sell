package com.example.springboot.utils;

import com.example.springboot.domain.OrderDetail;
import com.example.springboot.domain.OrderMaster;
import com.example.springboot.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

/**
 * @filename:       BeanUtilsTest
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月06日10:20
 * @description:   
 *                      
 */
public class BeanUtilsTest {
	public static void main(String[] args) {
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("1111");
		orderMaster.setBuyerPhone("123");

		OrderDto orderDto = new OrderDto();
		orderDto.setOrderId("2222");
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("1111");
		orderDto.setOrderDetails(Arrays.asList(orderDetail));

		BeanUtils.copyProperties(orderMaster, orderDto);

		System.out.println("main.orderMaster.toString()==>" + orderMaster.toString());
		System.out.println("main.orderDto.toString()==>" + orderDto.toString());


	}
    
}
