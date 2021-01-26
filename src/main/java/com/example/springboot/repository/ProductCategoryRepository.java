package com.example.springboot.repository;

import com.example.springboot.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * @author 			ZF
 * @date 			2020/8/19 
 * @parameter 		
 * @return 			 
 * @description		
 *      商品类目数据库接口
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
	/**
	 * @author 			ZF
	 * @date 			2020/9/3
	 * @parameter 		[categoryTypeList]
	 * @return 			java.util.List<com.example.springboot.domain.ProductCategory>
	 * @description
	 *      按照类目标号查找
	 **/
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
