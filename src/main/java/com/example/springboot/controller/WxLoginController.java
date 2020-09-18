package com.example.springboot.controller;

import com.example.springboot.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @filename:       WxLoginController
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月18日14:32
 * @description:   
 *                      
 */
@Slf4j
@Controller
@RequestMapping("/wx")
@Component
public class WxLoginController {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String encode = URLEncoder.encode("http://fzh.nat300.top/wx/callback", "UTF-8");
		System.out.println("main.encode==>" + encode);
	}

	@Value("${oauth.wx.appid}")
	private String appid;

	@Value("${oauth.wx.appsecret}")
	private String appsecret;

	@Value("${oauth.callback.http}")
	private String http;


	@GetMapping("/login")
	public String login() {
		log.info("....appid:{}",appid);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid +
				"&redirect_uri=" + http +
				"&response_type=code" +
				"&scope=snsapi_userinfo" +
				"&state=123#wechat_redirect";
		return "redirect:" + url;
	}

	@PostMapping("/callback")
	public String wxcallback(String code, ModelMap map) throws IOException {
		// 第二步：通过code换取网页授权access_token
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
		String resultString = HttpClientUtil.doGet(url);
		JSONObject jsonObject = JSONObject.fromObject(resultString);
		String openId = jsonObject.getString("openid");
		String access_Token = jsonObject.getString("access_token");

		System.out.println("access_Token" + access_Token);

		// 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_Token + "&openid=" + openId + "&lang=zh_CN";
		String userInfoJson = HttpClientUtil.doGet(url);
		System.out.println("UserInfo:" + userInfoJson);

		// 微信帐号做来一个关联，来关联我们的账号体系
		// 此处实现自己的保存用户信息逻辑
		return "redirect:/gohome?openid=" + openId;
	}
    
}
