package com.example.springboot.converter;

import com.example.springboot.domain.OrderDetail;
import com.example.springboot.dto.CarDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @filename:       OrderDetail2CarDto
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月06日15:53
 * @description:   
 *          订单详情转购物车
 */
public class OrderDetail2CarDto {

	/**
	 * @author 		   ZF
	 * @date 		   2020/9/6
	 * @parameter 	   [orderDetail]
	 * @return 		   com.example.springboot.dto.CarDto
	 * @description
	 *        订单详情转购物车
	 **/
	public static CarDto covert(OrderDetail orderDetail) {
		return new CarDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
	}

	/**
	 * @author 		   ZF
	 * @date 		   2020/9/6
	 * @parameter 	   [orderDetails]
	 * @return 		   java.util.List<com.example.springboot.dto.CarDto>
	 * @description
	 *       订单详情转购物车 批量
	 **/
	public static List<CarDto> covert(List<OrderDetail> orderDetails) {
		return orderDetails.stream().map(OrderDetail2CarDto :: covert).collect(Collectors.toList());
	}
    
}
