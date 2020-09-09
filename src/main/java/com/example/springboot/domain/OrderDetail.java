package com.example.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @filename:       OrderDetail
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月03日12:40
 * @description:
 *     订单明细表
 */
@Data
@Entity
@DynamicUpdate
public class OrderDetail {

	/** 订单明细表 id */
	@Id
	private String detailId;

	/** 订单主表 id */
	private String orderId;

	/** 商品id */
	private String productId;

	/** 商品名称 */
	private String productName;

	/** 商品单价 */
	private BigDecimal productPrice;

	/** 商品数量 */
	private Integer productQuantity;

	/** 商品小图 */
	private String productIcon;

	/** 创建时间 */
	private String createTime;

	/** 修改时间 */
	private String updateTime;
}
