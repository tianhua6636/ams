package com.th.ams.utils.httpclient;

/**
 * @author : wangtianhua
 * @date : 2019/08/20 16:33
 * @description : DOTO
 */
public enum ResultCode {
	// 正确请求
	SUCCESS(200, "success"),
	// 请求错误
	ERROR(500, "failure");

	/** 主键 */
	private final Integer code;

	/** 描述 */
	private final String desc;

	ResultCode(final Integer code, final String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}
}
