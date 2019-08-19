package com.th.ams.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/19 18:54
 * @description : 封装响应实体类
 */
public class ResponseEntity implements Serializable {
	private static final long serialVersionUID = -2941124394434003395L;

	private static final ObjectMapper MAPPER = new ObjectMapper();
	// 响应业务状态
	private Integer status;
	// 响应消息
	private String msg;
	// 响应中的数据
	private Object data;
	// 不使用
	private String isSuccess;

	private ResponseEntity(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private ResponseEntity(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}

	public ResponseEntity() {

	}

	/**
	 * 功能描述: <br>
	 * 返回响应信息
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:14
	 * @params : [status, msg, data]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity build(Integer status, String msg, Object data) {
		return new ResponseEntity(status, msg, data);
	}

	/**
	 * 功能描述: <br>
	 * 成功返回数据响应
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:20
	 * @params : [data]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity success(Object data) {
		return new ResponseEntity(data);
	}

	/**
	 * 功能描述: <br>
	 * 不返回数据（null）
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:20
	 * @params : []
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity success() {
		return new ResponseEntity(null);
	}

	/**
	 * 功能描述: <br>
	 * 500 错误不返回数据（null）
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:22
	 * @params : [msg]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity errorMsg(String msg) {
		return new ResponseEntity(500, msg, null);
	}

	/**
	 * 功能描述: <br>
	 * 501 错误返回
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:23
	 * @params : [data]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity errorMap(Object data) {
		return new ResponseEntity(501, "error", data);
	}

	/**
	 * 功能描述: <br>
	 * 502 错误
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:23
	 * @params : [msg]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity errorTokenMsg(String msg) {
		return new ResponseEntity(502, msg, null);
	}

	/**
	 * 功能描述: <br>
	 * 555 错误
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:24
	 * @params : [msg]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity errorException(String msg) {
		return new ResponseEntity(555, msg, null);
	}

	/**
	 * 功能描述: <br>
	 * 是否成功
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:14
	 * @params : [data]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity isSuccess(Object data) {
		return new ResponseEntity(data);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 功能描述: <br>
	 * 将json结果集转化为ExecuteResult对象，需要转换的对象是一个类
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:12
	 * @params : [jsonData, clazz]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity formatToModel(String jsonData, Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData, ResponseEntity.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isObject()) {
				obj = MAPPER.readValue(data.traverse(), clazz);
			} else if (data.isTextual()) {
				obj = MAPPER.readValue(data.asText(), clazz);
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 功能描述: <br>
	 * 没有object对象的转化
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:13
	 * @params : [json]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity format(String json) {
		try {
			return MAPPER.readValue(json, ResponseEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述: <br>
	 * Object是集合转化，需要转换的对象是一个list
	 * @author : wangtianhua
	 * @date : 2019/08/19 19:13
	 * @params : [jsonData, clazz]
	 * @return : com.th.ams.common.ResponseEntity
	 */
	public static ResponseEntity formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size() > 0) {
				obj = MAPPER.readValue(data.traverse(),
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		} catch (Exception e) {
			return null;
		}
	}
}
