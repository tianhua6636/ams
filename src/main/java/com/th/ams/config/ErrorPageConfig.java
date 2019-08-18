package com.th.ams.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author : wangtianhua
 * @date : 2019/08/17 12:58
 * @description : DOTO
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
	// private static final Logger logger = LoggerFactory.getLogger(ErrorPageConfig.class);

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		// 1、按错误的类型显示错误的网页
		// 错误类型为400，错误的请求，不是认证信息有问题，就是表示格式或HTTP库本身有问题
		ErrorPage e400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
		// 错误类型为403，客户端请求的结构正确，但是服务器不想处理它
		ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
		// 错误类型为404，找不到网页的，默认显示404.html网页
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		// 错误类型为500，表示服务器响应错误，默认显示500.html网页
		ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		// 错误类型为503，资源不足可能会导致
		ErrorPage e503 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/503");

		registry.addErrorPages(e400,e403,e404, e500,e503);
	}
}
