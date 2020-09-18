package com.example.springboot.controller;

import com.example.springboot.utils.WxLoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @filename:       WxPortalController
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月16日16:24
 * @description:   
 *                      
 */
@Slf4j
@RestController
@RequestMapping("/wx")
@Component
public class WxPortalController {

	@GetMapping("/checkSignature")
	public String authGet(@RequestParam(name = "signature") String signature, @RequestParam(name = "timestamp") String timestamp,
						@RequestParam(name = "nonce") String nonce, @RequestParam(name = "echostr") String echostr) throws IllegalAccessException {

		log.info("收到来自微信服务器的验证消息:{},{},{},{}",signature,timestamp,nonce,echostr);
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalAccessException("请求参数非法,请核实");
		}
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalAccessException("请求参数非法,请核实");
		}
		if (WxLoginUtil.checkSignature(timestamp, nonce, signature)) {
			log.info("验证成功.");
			return echostr;
		}
		return "验证失败";
	}

}
