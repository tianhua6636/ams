package com.th.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : wangtianhua
 * @date : 2019/08/17 16:15
 * @description : 跳转页面
 */
@Controller
@RequestMapping({"/view"})
public class ShowViewController {

	@GetMapping({"/index"})
	public String indexView(){
		return "page/index";
	}

	@GetMapping({"/dashboard2"})
	public String dashboard2View(Model model){
		model.addAttribute("name","TOM");
		return "page/dashboard2";
	}
}
