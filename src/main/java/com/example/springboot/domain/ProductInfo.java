package com.example.springboot.domain;

import com.example.springboot.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @filename:       ProductInfo
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月24日22:15
 * @description:   
 *        商品信息实体类
 */
@Entity
@Data
public class ProductInfo {
	/** 商品Id */
	@Id
	@GeneratedValue
	private String productId;

	/** 商品名称 */
	private String productName;

	/** 商品单价 */
	private BigDecimal productPrice;

	/** 商品库存 */
	private Integer productStock;

	/** 商品描述 */
	private String productDescription;

	/** 商品小图 */
	private String productIcon;

	/** 商品状态 0上架 1下架*/
	private Integer productStatus = ProductStatusEnum.UP.getCode();

	/** 类目编号 */
	private Integer categoryType;

}
