package com.example.springboot.exception;

import com.example.springboot.enums.ResultEnum;

/**
 * @filename:       SellException
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月04日13:25
 * @description:   
 *         异常处理类
 */
public class SellException extends RuntimeException {
	private final Integer code;

	public SellException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

	public SellException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}
