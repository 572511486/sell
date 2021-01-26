package com.example.springboot.repository;

import com.example.springboot.domain.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @filename:       OrderMasterRepository
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月03日12:47
 * @description:   
 *       商品主表数据库接口
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
	/**
	 * @author 			ZF
	 * @date 			2020/9/3
	 * @parameter 		[buyerOpenId, pageable]
	 * @return 			org.springframework.data.domain.Page<com.example.springboot.domain.OrderMaster>
	 * @description
	 *      通过用户微信openId查找订单
	 **/
	Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

	/**
	 * @author 		   ZF
	 * @date 		   2020/9/8
	 * @parameter 	   [buyerOpenId, orderId]
	 * @return 		   com.example.springboot.domain.OrderMaster
	 * @description
	 *         通过用户openid和orderid查找订单
	 **/
	OrderMaster findByBuyerOpenidAndOrderId(String buyerOpenId, String orderId);
}
