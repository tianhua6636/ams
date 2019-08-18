package com.th.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : wangtianhua
 * @date : 2019/08/17 13:24
 * @description : 错误页面跳转
 */
@Controller
@RequestMapping({"/error"})
public class ErrorPageController {

	@RequestMapping(value = "/400")
	public String error400() {
		return "error/error-400";
	}

	@RequestMapping(value = "/403")
	public String error403() {
		return "error/error-403";
	}

	@RequestMapping(value = "/404")
	public String error404() {
		return "error/error-404";
	}

	@RequestMapping(value = "/500")
	public String error500() {
		return "error/error-500";
	}

	@RequestMapping(value = "/503")
	public String error503() {
		return "error/error-503";
	}

}
