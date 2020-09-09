package com.example.springboot.service;

import com.example.springboot.domain.ProductInfo;
import com.example.springboot.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @filename:       ProductIndoService
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月24日22:50
 * @description:   
 *       商品信息接口
 */
public interface ProductInfoService {

    List<ProductInfo> findAllUp();

    List<ProductInfo> findAllDown();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo findOne(String productId);

    ProductInfo save(ProductInfo productInfo);

    /**
     * @author 			FZH
     * @date 			2020/9/4
     * @parameter 		[carDtoList]
     * @return 			void
     * @description
     *     增加库存
     **/
    void increaseStock(List<CarDto> carDtoList);

    /**
     * @author 			FZH
     * @date 			2020/9/4
     * @parameter 		[carDtoList]
     * @return 			void
     * @description
     *    减少库存
     **/
    void decreaseStock(List<CarDto> carDtoList);

}
