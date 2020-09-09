package com.example.springboot.repository;

import com.example.springboot.domain.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
	@Autowired
	private ProductCategoryRepository reportory;

	@Test
	public void findOneTest() {
		ProductCategory category = reportory.findOne(1);
		System.out.println("findOneTest.category.toString()==>" + category.toString());
	}

	@Test
	public void saveTest() {
		ProductCategory category = reportory.save(new ProductCategory("女生最爱", 2));
		System.out.println("findOneTest.category.toString()==>" + category.toString());
	}

	@Test
	public void finaAllTest() {
		List<ProductCategory> all = reportory.findAll();
		for (ProductCategory category : all) {
			System.out.println("finaAllTest.category.toString()==>" + category.toString());
		}
	}

	@Test
	public void updateTest() {
		ProductCategory category = reportory.findOne(2);
		category.setCategoryName("女生最爱");
		ProductCategory category1 = reportory.saveAndFlush(category);
		System.out.println("updateTest.category1.toString()==>" + category1.toString());
	}

	@Test
	public void findByCategoryTypeInTest() {
		List<Integer> typeList = Arrays.asList(1,2,3);
		List<ProductCategory> byCategoryTypeIn = reportory.findByCategoryTypeIn(typeList);
		for (ProductCategory category : byCategoryTypeIn) {
			System.out.println("findByCategoryTypeInTest.category.toString()==>" + category.toString());
		}
	}


}