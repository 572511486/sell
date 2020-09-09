package com.example.springboot.controller;

import com.example.springboot.domain.ProductCategory;
import com.example.springboot.domain.ProductInfo;
import com.example.springboot.service.ProductCategoryService;
import com.example.springboot.service.ProductInfoService;
import com.example.springboot.utils.ResultVoUtil;
import com.example.springboot.vo.ProductCategoryVo;
import com.example.springboot.vo.ProductInfoVo;
import com.example.springboot.vo.ResultVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @filename:       BuyerProductController
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月25日18:21
 * @description:   
 *     产品信息
 */
@Api
@RestController
@RequestMapping("/buyer/product/")
public class BuyerProductController {
	@Autowired
	private ProductCategoryService categoryService;
	@Autowired
	private ProductInfoService infoService;

	@GetMapping("/list")
	public ResultVo<List<ProductCategoryVo>> list() {
		// 所有上架商品
		List<ProductInfo> allUp = infoService.findAllUp();

		// 上架商品类目
		List<Integer> categoryTypeList = allUp.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
		List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(categoryTypeList);

		List<ProductCategoryVo> categoryVoList = new ArrayList<>();
		for (ProductCategory productCategory : byCategoryTypeIn) {
			Integer categoryType = productCategory.getCategoryType();
			ProductCategoryVo categoryVo = new ProductCategoryVo();
			BeanUtils.copyProperties(productCategory, categoryVo);

			List<ProductInfoVo> infoVoList = new ArrayList<>();
			for (ProductInfo productInfo : allUp) {
				if (!categoryType.equals(productInfo.getCategoryType())) {
					continue;
				}
				ProductInfoVo productInfoVo = new ProductInfoVo();
				BeanUtils.copyProperties(productInfo, productInfoVo);
				infoVoList.add(productInfoVo);
			}
			categoryVo.setProductInfos(infoVoList);
			categoryVoList.add(categoryVo);
		}
		return ResultVoUtil.success("成功", categoryVoList);
	}
    
}
