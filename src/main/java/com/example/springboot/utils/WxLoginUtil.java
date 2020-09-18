package com.example.springboot.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * @filename:       WxLoginUtil
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年09月17日16:22
 * @description:   
 *         微信授权帮助类
 */
public class WxLoginUtil {

	public static boolean checkSignature(String timestamp, String nonce, String signature) {
		String token = "fzh572511486";
		String[] arr = {token, timestamp, nonce};
		Arrays.sort(arr);

		StringBuilder stringBuilder = new StringBuilder();
		for (String s : arr) {
			stringBuilder.append(s);
		}
		return DigestUtils.sha1Hex(stringBuilder.toString()).equals(signature);
	}
}
