package com.example.springboot.converter;

import org.springframework.beans.BeanUtils;

import com.example.springboot.domain.OrderMaster;
import com.example.springboot.dto.OrderDto;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @filename:       OrderMaster2OrderDto
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月06日14:54
 * @description:   
 *        OrderMaste TO OrderDto 转换器
 */
public class OrderMaster2OrderDto {
	/**
	 * @author 		   FZH
	 * @date 		   2020/9/6
	 * @parameter 	   [orderMaster]
	 * @return 		   com.example.springboot.dto.OrderDto
	 * @description
	 *      OrderMaster 转 OrderDto
	 **/
	public static OrderDto convert(OrderMaster orderMaster) {
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(orderMaster, orderDto);
		return orderDto;
	}

	/**
	 * @author 		   FZH
	 * @date 		   2020/9/6
	 * @parameter 	   [orderMasterList]
	 * @return 		   java.util.List<com.example.springboot.dto.OrderDto>
	 * @description
	 *     批量  OrderMaster 转 OrderDto
	 **/
	public static List<OrderDto> convert(List<OrderMaster> orderMasterList) {
		return orderMasterList.stream().map(OrderMaster2OrderDto::convert).collect(Collectors.toList());
	}
    
}
