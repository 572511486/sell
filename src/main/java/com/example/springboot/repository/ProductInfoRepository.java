package com.example.springboot.repository;

import com.example.springboot.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @filename:       ProductInfoRepository
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月24日22:20
 * @description:   
 *       商品信息数据库接口
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
	/**
	 * @author 			ZF
	 * @date 			2020/8/24
	 * @parameter 		[status]
	 * @return 			java.util.List<com.example.springboot.domain.ProductInfo>
	 * @description
	 *     根据状态查询商品信息
	 **/
	List<ProductInfo> findByProductStatus(Integer status);

}
