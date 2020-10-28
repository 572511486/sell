package com.example.springboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @filename:       ProductInfoVo
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月25日18:36
 * @description:   
 *      商品信息页面返回实体
 */
@Data
public class ProductInfoVo {

	/** 商品ID */
	@JsonProperty("id")
	private String productId;

	/** 商品名称 */
	@JsonProperty("name")
	private String productName;

	/** 商品单价 */
	@JsonProperty("price")
	private BigDecimal productPrice;

	/** 商品描述 */
	@JsonProperty("description")
	private String productDescription;

	/** 商品小图 */
	@JsonProperty("icon")
	private String productIcon;

}
