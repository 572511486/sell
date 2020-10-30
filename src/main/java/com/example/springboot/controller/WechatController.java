package com.example.springboot.controller;

import com.example.springboot.config.ProjectUrlConfig;
import com.example.springboot.config.WechatMpAccountConfig;
import com.example.springboot.enums.ResultEnum;
import com.example.springboot.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @filename:       cla
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月24日13:55
 * @description:   
 *                      
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatController {
	@Autowired
	private WechatMpAccountConfig wechatMpAccountConfig;
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	@GetMapping("/authorize")
	public String authorize(String returnUrl) {
		log.info("auth.returnUrl==>{}", returnUrl);
		String callbackUrl = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
		String url = wxMpService.oauth2buildAuthorizationUrl(callbackUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
		return "redirect:" + url;
	}

	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl) {
		log.info("【用户信息】code={},state={}", code, returnUrl);
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
		try {
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException e) {
			log.info("【用户信息】e==>{}" ,e.getLocalizedMessage());
			throw new SellException(ResultEnum.WTCHAT_OAUTH_ERROR.getCode(), e.getError().getErrorMsg());
		}
		log.info("【用户信息】openid={}，accessToken={}", wxMpOAuth2AccessToken.getOpenId(), wxMpOAuth2AccessToken.getAccessToken());

		String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+wxMpOAuth2AccessToken.getAccessToken()
																+"&openid=" + wxMpOAuth2AccessToken.getOpenId() + "&lang=zh_CN";
		return "redirect:" + userUrl;
	}

	/**
	 * @author 		   FZH   
	 * @date 		   2020/9/28
	 * @parameter 	   [returnUrl]
	 * @return 		   java.lang.String 
	 * @description		
	 *    用户同意授权获取code，跳转到 returnUrl
	 **/
	@GetMapping("/qrAuthorize")
	public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
		log.info("【微信授权】 returnUrl==>{}", returnUrl);
		String qrUserinfo = projectUrlConfig.getWechatOpenAuthorize() + "/wechat/qrUserInfo";
		String url = wxMpService.oauth2buildAuthorizationUrl(qrUserinfo, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
		return "redirect:" + url;
	}

	/**
	 * @author 		   FZH
	 * @date 		   2020/9/28
	 * @parameter 	   [code, returnUrl]
	 * @return 		   java.lang.String
	 * @description
	 *        1.通过code换取accessToken
	 *        2.通过openId和access_token换取用户信息
	 **/
	@GetMapping("/qrUserInfo")
	public String qrUserInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
		log.info("WechatController.qrUserInfo.code==>{},returnUrl==>{}" , code,returnUrl);
		WxMpUser wxMpUser = new WxMpUser();
		try {
			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
		} catch (WxErrorException e) {
			log.error("【微信授权】 e==>{}", e.getMessage());
			throw new SellException(ResultEnum.WTCHAT_OAUTH_ERROR.getCode(), e.getError().getErrorMsg());
		}
		return wxMpUser.toString();
	}
    
}
