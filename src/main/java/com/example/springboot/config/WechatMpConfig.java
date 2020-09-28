package com.example.springboot.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @filename:       WechatMpConfig
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月25日16:28
 * @description:   
 *        微信公众号全局配置
 */
@Component
public class WechatMpConfig {
	@Autowired
	private WechatMpAccountConfig accountConfig;


	@Bean
	public WxMpService wxMpService(){
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}

	@Bean
	public WxMpConfigStorage wxMpConfigStorage(){
		WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setAppId(accountConfig.getMpAppid());
		wxMpConfigStorage.setSecret(accountConfig.getMpAppsecret());
		return wxMpConfigStorage;
	}
    
}
