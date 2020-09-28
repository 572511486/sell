package com.example.springboot.config;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class WechatMpConfigTest {

	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpConfigStorage configStorage;

	@Test
	public void wxMpService() {
		WxMpConfigStorage wxMpConfigStorage = wxMpService.getWxMpConfigStorage();
		String appId = wxMpConfigStorage.getAppId();
		String secret = wxMpConfigStorage.getSecret();
		log.info("appid:{},secret:{}",appId,secret);
	}

	@Test
	public void wxMpConfigStorage() {
		String appId = configStorage.getAppId();
		String secret = configStorage.getSecret();
		log.info("appid:{},secret:{}",appId,secret);
	}
}