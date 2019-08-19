package com.th.ams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.th.ams.common.ResponseEntity;
import com.th.ams.model.CityModel;
import com.th.ams.service.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/17 0:39
 * @description : DOTO
 */
@Controller
@RequestMapping({"/"})
public class HelloWorld {

	@Autowired
	private CityService cityService;

	@ApiOperation(value = "你好世界", notes="你好世界notes")
	@ApiImplicitParam(name = "token", value = "输入的token", required = true, dataType = "string")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path", dataType = "String"),
			@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "body", dataType = "User")
	})
	@ResponseBody
	@GetMapping({"{token}"})
	public String sayHi(@PathVariable String token,@RequestParam String tk){
		//ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/");
		return "Hello World! " + token + "," + tk;
	}

	@ApiOperation(value = "登录页面")
	@ApiImplicitParam(name = "id", value = "标记ID", required = true, dataType = "string")
	@GetMapping({"index"})
	public String index(){
		return "page/index";
	}

	@ApiOperation(value = "全部城市信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNumber",value = "当前页码",required = true,dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize",value = "每页显示的总条数",required = true,dataType = "Integer")
	})
	@ResponseBody
	@GetMapping({"cityList"})
	public ResponseEntity getCityList(@RequestParam("pageNumber") int pageNumber,
	                                                      @RequestParam("pageSize") int pageSize){
		PageHelper.startPage(pageNumber,pageSize);
		List<CityModel> list = cityService.getCityList();
		PageInfo<CityModel> pageInfo = new PageInfo<CityModel>(list);
		return ResponseEntity.success(pageInfo);
	}

}
