package com.example.springboot.dto;

import lombok.Data;

/**
 * @filename:       CarDto
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月04日16:43
 * @description:   
 *      购物车DTO 实体类
 */
@Data
public class CarDto {
	/** 商品id */
    private String productId;

    /** 商品数量 */
    private Integer productQuantity;

	public CarDto(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
