package com.example.springboot.controller;

import com.example.springboot.config.WechatMpAccountConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @filename:       WxLoginController
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月18日14:32
 * @description:   
 *           测试使用
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
@Component
@Deprecated
public class WeiXinController {
	@Autowired
	private WechatMpAccountConfig wechatMpAccountConfig;

	@GetMapping("/checkSignature")
	public String checkSignature(@RequestParam(name = "signature") String signature, @RequestParam(name = "timestamp") String timestamp,
						  @RequestParam(name = "nonce") String nonce, @RequestParam(name = "echostr") String echostr) {

		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(wechatMpAccountConfig.getMpAppid()); // 设置微信公众号的appid
		config.setSecret(wechatMpAccountConfig.getMpAppsecret()); // 设置微信公众号的app corpSecret
		config.setToken("promise"); // 设置微信公众号的token
		config.setAesKey(""); // 设置微信公众号的EncodingAESKey

		WxMpService wxService = new WxMpServiceImpl();
		wxService.setWxMpConfigStorage(config);

		log.info("收到来自微信服务器的验证消息:{},{},{},{}", signature, timestamp, nonce, echostr);
		log.info("【微信信息】:appid:{},secret:{}", wechatMpAccountConfig.getMpAppid(), wechatMpAccountConfig.getMpAppsecret());
		if (wxService.checkSignature(timestamp, nonce, signature)) {
			log.info("验证成功.");
			return echostr;
		}
		return "验证失败";
	}
    
}
