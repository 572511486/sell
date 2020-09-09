package com.example.springboot.enums;

import lombok.Getter;

/**
 * @filename:       ResultVoEnum
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月25日18:29
 * @description:   
 *       页面返回对象标识枚举
 */
@Getter
public enum ResultVoCodeEnum {
	//成功 0 ，失败 -1
	SUCCESS(0),
	ERROR(-1);

	ResultVoCodeEnum(Integer code) {
		this.code = code;
	}

	private final Integer code;
}
