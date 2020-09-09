package com.example.springboot.repository;

import com.example.springboot.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
	@Autowired
	private OrderDetailRepository repository;

	@Test
	public void saveTest() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailId("d20001");
		orderDetail.setOrderId("om10000");
		orderDetail.setProductId("p30002");
		orderDetail.setProductQuantity(1);
		orderDetail.setProductName("蒜蓉小龙虾");
		orderDetail.setProductPrice(BigDecimal.valueOf(128.00));
		orderDetail.setProductIcon("http://suanrongxiaolongxia.jpg");
		repository.save(orderDetail);
	}

	@Test
	public void findByOrderId() {
		Page<OrderDetail> orderDetails = repository.findByOrderId("om10000", new PageRequest(0, 1));
		Assert.assertNotEquals(0, orderDetails.getContent().size());
	}
}