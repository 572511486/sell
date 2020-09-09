package com.example.springboot.repository;

import com.example.springboot.domain.ProductInfo;
import com.example.springboot.enums.ProductStatusEnum;
import com.example.springboot.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

	@Autowired
	private ProductInfoRepository repository;

	@Test
	public void saveTest() {
		List<ProductInfo> saveList = new ArrayList<>();
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(KeyUtil.genUniqueKey());
		productInfo.setProductName("油焖大虾");
		productInfo.setProductPrice(new BigDecimal(138));
		productInfo.setProductStock(88);
		productInfo.setProductDescription("小龙虾干烧");
		productInfo.setProductIcon("http://ymdx.jpg");
		productInfo.setCategoryType(2);

		ProductInfo productInfo2 = new ProductInfo();
		productInfo2.setProductId(KeyUtil.genUniqueKey());
		productInfo2.setProductName("十三香小龙虾");
		productInfo2.setProductPrice(new BigDecimal(138));
		productInfo2.setProductStock(88);
		productInfo2.setProductDescription("十三香小龙虾");
		productInfo2.setProductIcon("http://ssxxlx.jpg");
		productInfo2.setCategoryType(2);
		saveList.add(productInfo);
		saveList.add(productInfo2);
		repository.save(productInfo);
		repository.save(productInfo2);
	}

	@Test
	public void findByProductStatus() {
		List<ProductInfo> productInfos = repository.findByProductStatus(ProductStatusEnum.UP.getCode());
		Assert.assertNotEquals(0, productInfos.size());
	}

}