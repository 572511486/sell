package com.example.springboot.service.impl;

import com.example.springboot.domain.ProductCategory;
import com.example.springboot.repository.ProductCategoryRepository;
import com.example.springboot.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @filename:       test
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月19日22:42
 * @description：
 *        商品类目Service实现
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategory findOne(Integer categoryId) {
		return repository.findOne(categoryId);
	}

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory category) {
		return repository.save(category);
	}
}
