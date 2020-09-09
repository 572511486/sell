package com.example.springboot.controller;

import com.example.springboot.converter.OrderForm2OrderDto;
import com.example.springboot.dto.OrderDto;
import com.example.springboot.enums.ResultEnum;
import com.example.springboot.exception.SellException;
import com.example.springboot.form.OrderForm;
import com.example.springboot.service.impl.OrderServiceImpl;
import com.example.springboot.utils.ResultVoUtil;
import com.example.springboot.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @filename:       BuyerOrderController
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月07日20:09
 * @description:   
 *     买家订单控制器
 */
@Api
@Slf4j
@RestController("/buyer/order")
public class BuyerOrderController {

	@Autowired
	private OrderServiceImpl orderService;

	@ApiOperation(value = "创建订单")
	@PostMapping("/create")
	public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.debug("【创建订单】 参数错误，orderForm={}", orderForm);
			throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDto orderDto = orderService.create(OrderForm2OrderDto.convert(orderForm));
		Map<String, String> orderMap = new HashMap<>();
		orderMap.put("orderId", orderDto.getOrderId());
		return ResultVoUtil.success(orderMap);
	}

	@ApiOperation(value = "订单列表")
	@GetMapping("/list")
	public ResultVo<List<OrderDto>> list(@RequestParam(value = "openid") String openid,
										 @RequestParam(value = "page",defaultValue = "0") Integer page,
										 @RequestParam(value = "size",defaultValue = "10") Integer size) {

		Page<OrderDto> orderDtoPage = orderService.findList(openid, new PageRequest(page, size));
		return ResultVoUtil.success(orderDtoPage.getContent());
	}

	@ApiOperation(value = "订单详情")
	@GetMapping("detail")
	public ResultVo<OrderDto> detail(@RequestParam(value = "openid") String openid,@RequestParam(value = "orderId") String orderId){
		OrderDto orderDto = orderService.findOne(openid,orderId);
		log.info("detail.orderDto==>" + orderDto);
		return ResultVoUtil.success(orderDto);
	}

	@ApiOperation(value = "取消订单")
	@PostMapping("/cancel")
	public ResultVo<String> cancel(@RequestParam(value = "openid") String openid,@RequestParam(value = "orderId") String orderId){
		OrderDto orderDto = orderService.findOne(openid,orderId);
		log.info("cancel.orderDto==>" + orderDto);
		orderService.cancel(orderDto);
		return ResultVoUtil.success("");
	}


    
}
