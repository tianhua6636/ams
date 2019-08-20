package com.th.ams.utils.httpclient;

/**
 * @author : wangtianhua
 * @date : 2019/08/20 16:33
 * @description :  HttpClient返回数据
 */
public class HttpClientResult<T> {
	public Integer code;
	public String msg;
	public T data;

	public HttpClientResult success(){
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getDesc();
		return this;
	}

	public HttpClientResult success(T data){
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getDesc();
		this.data = data;
		return this;
	}

	public HttpClientResult failure(){
		this.code = ResultCode.ERROR.getCode();
		this.msg = ResultCode.ERROR.getDesc();
		return this;
	}

	public HttpClientResult failure(T data){
		this.code = ResultCode.ERROR.getCode();
		this.msg = ResultCode.ERROR.getDesc();
		this.data = data;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
