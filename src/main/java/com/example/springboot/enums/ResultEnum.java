package com.example.springboot.enums;

import lombok.Getter;

/**
 * @filename:       ResultEnum
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月04日13:26
 * @description:   
 *                      
 */
@Getter
public enum ResultEnum {
	//枚举


	PARAM_ERROR(1,"参数错误"),

	PRODUCT_NOT_EXIST(10,"商品不存在"),

	PRODUCT_STOCK_NOT_ENOUGH(11,"商品库存不足"),

	ORDER_NOT_EXIST(12,"订单不存在"),

	ORDERDETAIL_NOT_ISEMPTY(13,"订单详情为空"),

	ORDER_STATU_ERROR(14,"订单状态不正确"),

	ORDER_UPDATE_ERROR(15,"订单更新失败"),

	PAY_STATU_ERROR(16,"订单支付状态不正确"),

	WTCHAT_OAUTH_ERROR(17,"微信授权异常"),
	;

	private final Integer code;
	private final String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
