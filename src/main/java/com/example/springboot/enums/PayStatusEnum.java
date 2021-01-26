package com.example.springboot.enums;

import lombok.Getter;

/**
 * @filename:       PayStatusEnum
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月02日22:34
 * @description:   
 *      订单支付状态
 */
@Getter
public enum PayStatusEnum {
	// 默认 0
	WAIT(0,"等待支付"),
	SUCCESS(1,"支付成功");

	private final Integer code;
	private final String display;

	PayStatusEnum(Integer code, String display) {
		this.code = code;
		this.display = display;
	}
}
