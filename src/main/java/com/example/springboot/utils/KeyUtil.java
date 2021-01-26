package com.example.springboot.utils;

import java.util.Random;

/**
 * @filename:       KeyUtil
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年09月04日13:56
 * @description:   
 *         数据库主键帮助类
 */
public class KeyUtil {

	/**
	 * @author 			ZF
	 * @date 			2020/9/4
	 * @parameter 		[]
	 * @return 			java.lang.String
	 * @description
	 *     生成唯一Key值
	 **/
	public static synchronized String genUniqueKey() {
		Random random = new Random();
		return System.currentTimeMillis() + String.valueOf(random.nextInt(999999));
	}
    
}
