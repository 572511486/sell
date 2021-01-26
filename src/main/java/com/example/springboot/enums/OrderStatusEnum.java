package com.example.springboot.enums;

import lombok.Getter;

/**
 * @filename:       OrderStatusEnum
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月02日22:36
 * @description:   
 *       订单状态
 */
@Getter
public enum  OrderStatusEnum {
	//默认 0， 新订单
	NEW(0,"新订单"),
	FINISHED(1,"完结"),
	CANCEL(2,"取消");

	private final Integer code;
	private final String display;

	OrderStatusEnum(Integer code, String display) {
		this.code = code;
		this.display = display;
	}
}
