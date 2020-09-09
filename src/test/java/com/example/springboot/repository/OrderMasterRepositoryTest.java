package com.example.springboot.repository;

import com.example.springboot.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
	@Autowired
	private OrderMasterRepository repository;

	@Test
	public void saveTest(){
		OrderMaster orderMaster = new OrderMaster();
		orderMaster.setOrderId("om10001");
		orderMaster.setBuyerAddress("江苏省南京市");
		orderMaster.setBuyerName("诺言");
		orderMaster.setBuyerOpenid("572511486");
		orderMaster.setBuyerPhone("18952207891");
		orderMaster.setOrderAmount(BigDecimal.valueOf(128));
		repository.save(orderMaster);
	}

	@Test
	public void findByBuyerOpenid() {
		PageRequest pageRequest = new PageRequest(0,1);
		Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid("572511486", pageRequest);
		int totalPages = byBuyerOpenid.getTotalPages();
		System.out.println("findByBuyerOpenid.totalPages==>" + totalPages);
		List<OrderMaster> content = byBuyerOpenid.getContent();
		Assert.assertNotEquals(0, content.size());

	}


	@Test
	public void findByBuyerOpenidAndOrderIdTest() {
		OrderMaster orderMaster = repository.findByBuyerOpenidAndOrderId("110110", "1599362953561965554");
		System.out.println("findByBuyerOpenid.totalPages==>" + orderMaster);

	}
}