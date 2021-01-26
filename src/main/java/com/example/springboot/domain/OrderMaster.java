package com.example.springboot.domain;

import com.example.springboot.enums.OrderStatusEnum;
import com.example.springboot.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @filename:       OrderMaster
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月02日22:23
 * @description:   
 *      订单主表
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {
	/** 订单主表id */
	@Id
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
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

}
