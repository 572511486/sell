package com.example.springboot.utils;

import com.example.springboot.enums.ResultVoCodeEnum;
import com.example.springboot.vo.ResultVo;

/**
 * @filename:       ResoultVoUtil
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         ZF
 * @createtime:     2020年08月26日13:34
 * @description:   
 *        页面返回数据帮助类
 */
public class ResultVoUtil {

	/**
	 * @author 			ZF
	 * @date 			2020/8/26
	 * @parameter 		[msg, data]
	 * @return 			com.example.springboot.vo.ResultVo<T>
	 * @description
	 *     返回成功数据
	 **/
	public static <T> ResultVo<T> success(T data) {
		return success("成功", data);
	}

	public static <T> ResultVo<T> success(String msg,T data) {
		return instance(ResultVoCodeEnum.SUCCESS.getCode(),msg,data);
	}

	/**
	 * @author 			ZF
	 * @date 			2020/8/26
	 * @parameter 		[msg, data]
	 * @return 			com.example.springboot.vo.ResultVo<T>
	 * @description
	 *    返回失败数据
	 **/
	public static <T> ResultVo<T> error(T data) {
		return error("失败",data);
	}

	public static <T> ResultVo<T> error(String msg,T data) {
		return instance(ResultVoCodeEnum.ERROR.getCode(),msg,data);
	}

	/**
	 * @author 			ZF
	 * @date 			2020/8/26
	 * @parameter 		[code, msg, data]
	 * @return 			com.example.springboot.vo.ResultVo
	 * @description
	 *       实例化返回对象
	 **/
	private  static <T> ResultVo<T> instance(Integer code,String msg,T data){
		ResultVo<T> resultVo = new ResultVo<>();
		resultVo.setData(data);
		resultVo.setCode(code);
		resultVo.setMsg(msg);
		return resultVo;
	}
}
