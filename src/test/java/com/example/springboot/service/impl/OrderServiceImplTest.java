package com.example.springboot.service.impl;

import com.example.springboot.domain.OrderDetail;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.enums.OrderStatusEnum;
import com.example.springboot.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
	@Autowired
	private OrderServiceImpl orderService;

	private final String BUYER_OPENID = "110110";
	private final String ORDER_ID = "1599362953561965554";
	@Test
	public void create() {
		OrderDto orderDto = new OrderDto();
		orderDto.setBuyerOpenid(BUYER_OPENID);
		orderDto.setBuyerAddress("湖南株洲市供销大厦");
		orderDto.setBuyerName("诺言");
		orderDto.setBuyerPhone("18952205220");

		OrderDetail orderDetail1 = new OrderDetail();
		orderDetail1.setProductId("1599210514540268529");
		orderDetail1.setProductQuantity(2);
		orderDto.setOrderDetails(Collections.singletonList(orderDetail1));
		orderService.create(orderDto);
		log.info("create.orderDetail1==>" + orderDetail1);

	}

	@Test
	public void findOne() {
		OrderDto orderDto = orderService.findOne(BUYER_OPENID,ORDER_ID);
		log.info("findOne.orderDto==>" + orderDto);
	}

	@Test
	public void findList() {
		Page<OrderDto> list = orderService.findList(BUYER_OPENID, new PageRequest(0, 2));
		log.info("findList.list.getContent()==>" + list.getContent());
	}

	@Test
	public void cancel() {
		OrderDto orderDto = orderService.findOne(ORDER_ID,null);
		OrderDto cancel = orderService.cancel(orderDto);
		Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), cancel.getOrderStatus());
	}

	@Test
	public void finish() {
		OrderDto orderDto = orderService.findOne(ORDER_ID,null);
		OrderDto cancel = orderService.finish(orderDto);
		Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), cancel.getOrderStatus());
	}

	@Test
	public void pay() {
		OrderDto orderDto = orderService.findOne(ORDER_ID,null);
		OrderDto cancel = orderService.pay(orderDto);
		Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), cancel.getPayStatus());
	}
}