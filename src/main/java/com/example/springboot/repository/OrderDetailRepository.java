package com.example.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.domain.OrderDetail;
import java.util.List;

/**
 * @filename:       OrderDetailRepository
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月03日13:29
 * @description:   
 *       订单详细表查询接口
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
	/**
	 * @author 			FZH
	 * @date 			2020/9/3
	 * @parameter 		[orderId, pageable]
	 * @return 			org.springframework.data.domain.Page<com.example.springboot.domain.OrderDetail>
	 * @description
	 *       根据订单主表Id分页查询订单明细表
	 **/
	Page<OrderDetail> findByOrderId(String orderId, Pageable pageable);

	/**
	 * @author 		   FZH
	 * @date 		   2020/9/4
	 * @parameter 	   [orderId]
	 * @return 		   java.util.List<com.example.springboot.domain.OrderDetail>
	 * @description
	 *          根据订单主表Id查询订单明细表
	 **/
	List<OrderDetail> findByOrderId(String orderId);

}
