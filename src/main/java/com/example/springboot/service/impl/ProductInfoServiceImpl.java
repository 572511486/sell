package com.example.springboot.service.impl;

import com.example.springboot.domain.ProductInfo;
import com.example.springboot.dto.CarDto;
import com.example.springboot.enums.ProductStatusEnum;
import com.example.springboot.enums.ResultEnum;
import com.example.springboot.exception.SellException;
import com.example.springboot.repository.ProductInfoRepository;
import com.example.springboot.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @filename:       ProductInfoServiceImpl
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月24日22:55
 * @description:   
 *                      
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoRepository repository;
	
	/**
	 * @author 			FZH
	 * @date 			2020/8/24 
	 * @parameter 		[]
	 * @return 			java.util.List<com.example.springboot.domain.ProductInfo> 
	 * @description		
	 *        查询所有已上架       		 
	 **/
	@Override
	public List<ProductInfo> findAllUp() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	/**
	 * @author 			FZH
	 * @date 			2020/8/24 
	 * @parameter 		[]
	 * @return 			java.util.List<com.example.springboot.domain.ProductInfo> 
	 * @description		
	 *      查询所有已下架
	 **/
	@Override
	public List<ProductInfo> findAllDown() {
		return repository.findByProductStatus(ProductStatusEnum.DOWN.getCode());
	}

	/**
	 * @author 			FZH
	 * @date 			2020/8/25
	 * @parameter 		[pageable]
	 * @return 			org.springframework.data.domain.Page<com.example.springboot.domain.ProductInfo>
	 * @description
	 *      查询所有
	 **/
	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo findOne(String productId) {
		return repository.findOne(productId);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

	/**
	 * @author 			FZH
	 * @date 			2020/9/4
	 * @parameter 		[carDtoList]
	 * @return 			void
	 * @description
	 *      增加库存
	 **/
	@Override
	@Transactional
	public void increaseStock(List<CarDto> carDtoList) {
		for (CarDto carDto : carDtoList) {
			ProductInfo productInfo = repository.findOne(carDto.getProductId());
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			productInfo.setProductStock(carDto.getProductQuantity() + productInfo.getProductStock());
			repository.save(productInfo);
		}
	}

	/**
	 * @author 			FZH
	 * @date 			2020/9/4
	 * @parameter 		[carDtoList]
	 * @return 			void
	 * @description
	 *      减少库存
	 **/
	@Override
	@Transactional
	public void decreaseStock(List<CarDto> carDtoList) {
		for (CarDto carDto : carDtoList) {
			ProductInfo productInfo = repository.findOne(carDto.getProductId());
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			int remainQuantity = productInfo.getProductStock() - carDto.getProductQuantity();
			if (remainQuantity < 0) {
				throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_ENOUGH);
			}
			productInfo.setProductStock(remainQuantity);
			repository.save(productInfo);
		}
	}
}
