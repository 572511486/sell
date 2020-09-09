package com.example.springboot.enums;

import lombok.Getter;

/**
 * @filename:       ProductInfoStatusEnum
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月24日22:41
 * @description:   
 *                      
 */
@Getter
public enum ProductStatusEnum {
	// 0 上架 1 下架
	UP(0,"上架"),
	DOWN(1,"下架");

	private final Integer code;
	private final String display;

	ProductStatusEnum(Integer code, String display) {
		this.code = code;
		this.display = display;
	}
}
