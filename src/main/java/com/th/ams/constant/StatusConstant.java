package com.th.ams.constant;

/**
 * @author : wangtianhua
 * @date : 2019/08/19 18:45
 * @description : 状态码枚举类
 */
public enum StatusConstant {
	/**
	 * 请登录 code
	 */
	LOGIN_STATUS_ERROR("10503","请登录!"),
	/**
	 * 数据不存在
	 */
	DATA_NOT_EXIST("10504","数据不存在!"),
	/**
	 * 参数错误
	 */
	PARAMS_ERROR("10508","参数错误!"),
	/**
	 * 重复请求校验
	 */
	TOKEN_CHECK_ERROR("10555","重复请求校验!"),
	/**
	 * 重复请求校验成功
	 */
	TOKEN_CHECK_SUCCESS("200","重复请求校验成功!"),
	/**
	 * 非法操作
	 */
	FAIL_EXCEPTION("2400","非法操作!"),

	CUSTOMER_UPDATE_CUSTOMER_NICKNAME_IS_EXIST("11816","11816"),
	CUSTOMER_UPDATE_CUSTOMER_NICKNAME_IS_NULL("11817","11817"),

	REGIST_SUCCESS("10000","注册成功!"),
	ACCOUNT_EXIST("10001","用户名已经注册!"),
	ACCOUNT_EMPTY("10502","帐号名称为空!"),
	PASSWORD_EMPTY("10505","密码为空!"),
	PASSWORD_LENGTH_ERROR("10512","密码长度错误!"),
	EMAIL_EMPTY("10513","电子邮件为空!"),
	EMAIL_FORMAT_INVALIDATE("10514","电子邮件格式无效!"),
	EMAIL_EXIST("10012","电子邮件已经注册!"),
	SUCCESS("200","成功!"),
	REQUEST_DATA_NULL("20000","请求对象为空!"),
	ACCOUNTINFO_NULL("20001","帐号信息为空!"),
	OVERLENGTH_ERROR("20002","Field length is to long!"),
	DB_ERROR("20003","The database throw an Exception!"),
	SERVICE_ERROR("20004","Service Error(Such as: exist or not exist...)!"),
	UNKNOW_ERROR("20005","Unknow Exception"),
	CUSTOMERINFO_NULL("20006","CustomerInfo is null!"),
	CUSTNAME_NULL("20009","CustName is null"),
	INSERT_EXCEPTION("20500","Insert Error(such as : insert row may be existuser the same as the owner...)!"),
	TOKEN_ERROR("20504","Token is empty or incorrect, you may be login first and get a correct token!"),
	TOKEN_EXPIRED("20506","Token is expired, please refresh token!"),
	REFRESH_TOKEN_ERROR("20507","refresh TOKEN error,you should login again and get a fresh token!"),
	PARAMS_EXCEPTION("20505","Parameter error!"),
	UPLOAD_SIZE_EXCEPTION("20510","Upload size exception!"),
	UPLOAD_TYPE_EXCEPTION("20511","Upload types exception!"),
	NATION_VALID_ERROR("90001","NATIONAL VALID ERROR!"),
	ORDER_DELETE_ERROR("50001","ORDER  DELETE ERROR!"),
	CONSTANT_VALID_ERROR("90002","CONSTANT VALID ERROR!"),
	VERIFYCODE_ERROR("220114","verfiCode error!"),
	;
	private String code;
	private String msg;

	StatusConstant(String code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
