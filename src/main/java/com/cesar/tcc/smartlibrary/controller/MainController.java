package com.cesar.tcc.smartlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "mainController")
@RequestMapping(value = "/")
public class MainController extends AppController
{

	@RequestMapping
	public String startMainPage(final ModelMap modelMap)
	{
		modelMap.addAttribute("loggedinuser", getPrincipal());

		return Constants.MAIN_PAGE;
	}

}
