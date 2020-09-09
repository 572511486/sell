package com.example.springboot.service;

import com.example.springboot.domain.ProductCategory;
import com.example.springboot.service.impl.ProductCategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

	@Autowired
	private ProductCategoryServiceImpl productCategoryService;
	
	@Test
	public void findOne() {
		ProductCategory one = productCategoryService.findOne(2);
		System.out.println("findOne.one.toString()==>" + one.toString());
	}

	@Test
	public void findAll() {
		List<ProductCategory> all = productCategoryService.findAll();
		for (ProductCategory category : all) {
			System.out.println("findAll.category.toString()==>" + category.toString());
		}
	}

	@Test
	public void findByCategoryTypeIn() {
		List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3));
		for (ProductCategory category : byCategoryTypeIn) {
			System.out.println("findByCategoryTypeIn.category.toString()==>" + category.toString());
		}
	}

	@Test
	public void save() {
		ProductCategory category = productCategoryService.save(new ProductCategory("老人最爱", 3));
		System.out.println("save.category.toString()==>" + category.toString());
	}
}