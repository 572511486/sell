package com.example.springboot.dto;

import com.example.springboot.domain.OrderDetail;
import com.example.springboot.enums.OrderStatusEnum;
import com.example.springboot.enums.PayStatusEnum;
import com.example.springboot.utils.serilaize.Dato2LongSerilaize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @filename:       OrderDto
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月03日20:14
 * @description:   
 *      订单信息Dto dto:data transfer object 数据传输对象
 *      JsonSerialize注解,主要应用于数据转换,该注解作用在该属性的getter()方法上。
 */
@Data
public class OrderDto {
	/** 订单主表id */
	private String orderId;

	/** 买家姓名 */
	private String buyerName;

	/** 买家电话 */
	private String buyerPhone;

	/** 买家地址 */
	private String buyerAddress;

	/** 买家微信openid */
	private String buyerOpenid;

	/** '订单总金额' */
	private BigDecimal orderAmount;

	/** 订单状态,默认0 新下单 */
	private Integer orderStatus = OrderStatusEnum.NEW.getCode();

	/** 支付状态,默认0 未支付 */
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	/** 创建时间 */
	@JsonSerialize(using = Dato2LongSerilaize.class)
	private Date createTime;

	/** 修改时间 */
	@JsonSerialize(using = Dato2LongSerilaize.class)
	private Date updateTime;

	/** 订单明细 **/
	List<OrderDetail> orderDetails = new ArrayList<>();
}
