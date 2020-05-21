/*
 * Copyright (c) 2014-2020 杭州派迩信息技术有限公司 All Rights Reserved.
 * ProjectName: pi-cloud
 * ModuleName: picloud-common-core
 * FileName: ActionResult.java
 * Author: YuZheng
 * Date: 2020/04/27 16:42:27
 * Version: 1.0
 * LastModified: 2020/04/27 15:33:27
 */
package com.tangyiner.world.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tangyiner.world.core.handler.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后端服务返回统一格式对象, 序列化成json字符串返回
 * {"requestId":"12345", "code": 0, "message": null, "data": null }
 * code: 状态码, 0成功, 1一般业务异常, -1其他异常, -2授权异常, 令牌错误或令牌过期
 * message: 错误消息
 * data: 数据
 * @author yuzheng
 * @see ResponseBody
 */
@JsonInclude(Include.ALWAYS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionResult {

	/**
	 * 请求id
	 */
	private String requestId;

	/**
	 * code: 状态码, 0成功, 1一般业务异常, -1其他异常, -2授权异常, 令牌错误或令牌过期
	 */
	private Integer code = ActionResultCode.SUCCESS.getValue();

	/**
	 * message: 错误消息
	 */
	private String message;

	/**
	 * data: 数据
	 */
	private Object data;
}
