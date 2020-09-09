package com.example.springboot.service.impl;

import com.example.springboot.converter.OrderDetail2CarDto;
import com.example.springboot.converter.OrderMaster2OrderDto;
import com.example.springboot.domain.OrderDetail;
import com.example.springboot.domain.OrderMaster;
import com.example.springboot.domain.ProductInfo;
import com.example.springboot.dto.CarDto;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.enums.OrderStatusEnum;
import com.example.springboot.enums.PayStatusEnum;
import com.example.springboot.enums.ResultEnum;
import com.example.springboot.exception.SellException;
import com.example.springboot.repository.OrderDetailRepository;
import com.example.springboot.repository.OrderMasterRepository;
import com.example.springboot.repository.ProductInfoRepository;
import com.example.springboot.service.OrderService;
import com.example.springboot.service.ProductInfoService;
import com.example.springboot.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @filename:       OrderServiceImpl
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月03日20:25
 * @description:   
 *      订单service层实现类
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductInfoRepository productInfoRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	@Autowired
	private ProductInfoService productInfoService;

	/**
	 * @author 		   FZH
	 * @date 		   2020/9/4
	 * @parameter 	   [orderDto]
	 * @return 		   com.example.springboot.dto.OrderDto
	 * @description
	 *         创建订单
	 **/
	@Override
	@Transactional
	public OrderDto create(OrderDto orderDto) {
		BigDecimal orderAmount = BigDecimal.ZERO;
		String orderId = KeyUtil.genUniqueKey();
		//1.查询商品(数量)
		for (OrderDetail orderDetail : orderDto.getOrderDetails()) {
			ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//2.计算总价
			orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
			//3.保存数据(OrderMaster、OrderDetail)
			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetail.setDetailId(KeyUtil.genUniqueKey());
			orderDetail.setOrderId(orderId);
			orderDetailRepository.save(orderDetail);
		}
		OrderMaster orderMaster = new OrderMaster();
		orderDto.setOrderId(orderId);
		BeanUtils.copyProperties(orderDto, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMasterRepository.save(orderMaster);

		//4.扣库存
		List<CarDto> carDtoList = orderDto.getOrderDetails().stream().map(e -> new CarDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productInfoService.decreaseStock(carDtoList);
		return orderDto;
	}

	@Override
	public OrderDto findOne(String openId,String orderId) {
		OrderMaster orderMaster = orderMasterRepository.findByBuyerOpenidAndOrderId(openId,orderId);
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(orderMaster, orderDto);
		List<OrderDetail> orderDetailPage = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
		if (orderDetailPage.isEmpty()) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_ISEMPTY);
		}
		orderDto.setOrderDetails(orderDetailPage);
		return orderDto;
	}

	@Override
	public OrderDto findOne(String orderId) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(orderMaster, orderDto);
		List<OrderDetail> orderDetailPage = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
		if (orderDetailPage.isEmpty()) {
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_ISEMPTY);
		}
		orderDto.setOrderDetails(orderDetailPage);
		return orderDto;
	}

	@Override
	public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
		Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
		return new PageImpl<>(OrderMaster2OrderDto.convert(orderMasters.getContent()),pageable,orderMasters.getTotalElements());
	}

	@Override
	@Transactional
	public OrderDto cancel(OrderDto orderDto) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		// 1.判断状态
		if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
			log.error("【取消订单】 订单状态不正确。orderStatu={},orderid={}",orderMaster.getOrderStatus(),orderMaster.getOrderId());
			throw new SellException(ResultEnum.ORDER_STATU_ERROR);
		}
		// 2.修改状态
		orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
		BeanUtils.copyProperties(orderDto, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【取消订单】 订单更新失败.orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
		}
		// 3.返库存
		List<OrderDetail> orderDetails = orderDto.getOrderDetails();
		if (orderDetails.isEmpty()) {
			log.error("【取消订单】 订单详情为空，orderDto={}",orderDto);
			throw new SellException(ResultEnum.ORDERDETAIL_NOT_ISEMPTY);
		}
		productInfoService.increaseStock(OrderDetail2CarDto.covert(orderDetails));

		// 4、已支付退款
		if (PayStatusEnum.SUCCESS.getCode().equals(orderMaster.getPayStatus())) {
			// TODO 退款逻辑
		}
		return orderDto;
	}

	@Override
	@Transactional
	public OrderDto finish(OrderDto orderDto) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		// 1.判断状态
		if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
			log.error("【完结订单】 订单状态不正确。orderStatu={},orderid={}",orderMaster.getOrderStatus(),orderMaster.getOrderId());
			throw new SellException(ResultEnum.ORDER_STATU_ERROR);
		}
		//2、完结订单
		orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
		BeanUtils.copyProperties(orderDto, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【完结订单】 订单更新失败.orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
		}
		return orderDto;
	}

	@Override
	@Transactional
	public OrderDto pay(OrderDto orderDto) {
		OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
		if (orderMaster == null) {
			throw new SellException(ResultEnum.ORDER_NOT_EXIST);
		}
		//1、判断订单状态
		if (!OrderStatusEnum.NEW.getCode().equals(orderMaster.getOrderStatus())) {
			log.error("【支付订单】 订单状态不正确。orderStatu={},orderid={}",orderMaster.getOrderStatus(),orderMaster.getOrderId());
			throw new SellException(ResultEnum.ORDER_STATU_ERROR);
		}
		//2、判断支付状态
		if (!PayStatusEnum.WAIT.getCode().equals(orderMaster.getPayStatus())) {
			log.error("【支付订单】 支付状态不正确。orderId={},payStatus={}",orderMaster.getOrderId(),orderMaster.getPayStatus());
			throw new SellException(ResultEnum.PAY_STATU_ERROR);
		}
		//3、设置支付状态
		orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
		BeanUtils.copyProperties(orderDto, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);
		if (updateResult == null) {
			log.error("【支付订单】 订单更新失败.orderMaster={}",orderMaster);
			throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
		}
		return orderDto;
	}
}
