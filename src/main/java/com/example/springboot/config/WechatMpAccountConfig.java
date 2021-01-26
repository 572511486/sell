package com.example.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @filename:       WechatMpAccountConfig
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月25日16:38
 * @description:   
 *    微信账号配置
 */
@Data
@Component
/* 读取配置文件application.yml  使wechat下的属性和当前类属性匹配 */
@ConfigurationProperties(prefix = "wechat")
public class WechatMpAccountConfig {
	private String mpAppid;
	private String mpAppsecret;



	// @Value("${oauth.wx.appid}")
	// private String appid;
	//
	// @Value("${oauth.wx.appsecret}")
	// private String appsecret;
	//
	// @Value("${oauth.callback.http}")
	// private String http;
}
