package com.example.springboot.service;

import com.example.springboot.domain.ProductCategory;

import java.util.List;

/**
 * @filename:       test
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月19日22:42
 * @description：
 *       商品类目Service
 */
public interface ProductCategoryService {
	/**
	 * @author 			ZF   
	 * @date 			2020/8/19 
	 * @parameter 		[categoryId]
	 * @return 			com.example.springboot.domain.ProductCategory
	 * @description		
	 *        根据商品ID查找
	 **/
	ProductCategory findOne(Integer categoryId);

	/**
	 * @author 			ZF
	 * @date 			2020/8/19
	 * @parameter 		[]
	 * @return 			java.util.List<com.example.springboot.domain.ProductCategory>
	 * @description
	 *      查询所有商品类目
	 **/
	List<ProductCategory> findAll();

	/**
	 * @author 			ZF
	 * @date 			2020/8/19
	 * @parameter 		[categoryTypeList]
	 * @return 			java.util.List<com.example.springboot.domain.ProductCategory>
	 * @description
	 *       按商品类目编号范围查找
	 **/
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

	/**
	 * @author 			ZF
	 * @date 			2020/8/19
	 * @parameter 		[category]
	 * @return 			com.example.springboot.domain.ProductCategory
	 * @description
	 *          保存商品类目
	 **/
	ProductCategory save(ProductCategory category);
}
