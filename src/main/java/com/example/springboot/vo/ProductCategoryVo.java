package com.example.springboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @filename:       ProductCategoryVo
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月25日18:32
 * @description:   
 *       产品类目页面返回实体类
 */
@Data
public class ProductCategoryVo {

    /** 类目名称 返回页面时 key：name  */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编号.*/
    @JsonProperty("type")
    private Integer categoryType;

    /** 商品信息 */
    @JsonProperty("foods")
    private List<com.example.springboot.vo.ProductInfoVo> productInfos;

}
