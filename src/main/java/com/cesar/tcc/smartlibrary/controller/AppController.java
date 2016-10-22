package com.cesar.tcc.smartlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping("/")
	public ModelAndView goToIndex() {
		final ModelAndView modelAndView = new ModelAndView("index");

		return modelAndView;
	}

}
