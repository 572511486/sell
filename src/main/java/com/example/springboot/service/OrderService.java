package com.example.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springboot.dto.OrderDto;

/**
 * @filename:       OrderMasterService
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月03日20:12
 * @description:   
 *        订单service接口
 */
public interface OrderService {
	/** 创建订单 */
	OrderDto create(OrderDto orderDto);

	/** 查询单个订单 */
	OrderDto findOne(String openId,String orderId);

	/** 查询单个订单 */
	OrderDto findOne(String orderId);

	/** 查询订单列表 */
	Page<OrderDto> findList(String buyerOpenId, Pageable pageable);

	/** 取消订单 */
	OrderDto cancel(OrderDto orderDto);

	/** 完结订单 */
	OrderDto finish(OrderDto orderDto);

	/** 支付订单 */
	OrderDto pay(OrderDto orderDto);
}
