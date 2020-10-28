package com.example.springboot.vo;

import lombok.Data;

/**
 * @filename:       ResultVo
 * @copyright:      版权所有 2020-2025 南京国睿信维软件有限公司
 * @version:        V1.0
 * @author:         FZH
 * @createtime:     2020年08月25日18:27
 * @description:   
 *       页面返回对象 Result View Object
 */
@Data
public class ResultVo<T> {
    // 请求状态
    private Integer code;
    // 返回消息
    private String msg;
    // 返回数据
    private T data;
}
