package com.until.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class rsa1Controller {
	@RequestMapping("/emps")
	public ModelAndView toLogin() {
		ModelAndView model = new ModelAndView();
		System.out.println("Hello !!!");
		model.setViewName("rsa");
		return model;
	}
}
