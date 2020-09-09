package com.example.springboot.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @filename:       OrderForm
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月07日20:11
 * @description:   
 *     订单表单
 */
@Data
public class OrderForm {
	/** 买家姓名 */
	@NotEmpty(message = "买家姓名不能为空")
	private String name;

	/** 买家手机号 */
	@NotEmpty(message = "买家手机号不能为空")
	private String phone;

	/** 买家地址 */
	@NotEmpty(message = "买家地址不能为空")
	private String address;

	/** 买家openid */
	@NotEmpty(message = "买家openid不能为空")
	private String openid;

	/** 购物车 */
	@NotEmpty(message = "购物车不能为空")
	private String items;

}
