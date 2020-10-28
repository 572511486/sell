package com.example.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @filename:       ProductCategory
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月24日22:15
 * @description:
 *         商品类目实体类
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

	@Id
	@GeneratedValue   // 默认主键自增长策略
	private Integer categoryId;

	/** 类目名称 */
	private String categoryName;

	/** 类目编号.*/
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;


	public ProductCategory() {
	}

	public ProductCategory(String categoryName, Integer categoryType) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

}
