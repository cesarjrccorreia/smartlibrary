package com.cesar.tcc.smartlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "emprestimoController")
@RequestMapping(value = "/emprestimo")
public class EmprestimoController extends AppController
{

	@RequestMapping(value = "/renovar")
	public String renovar(final ModelMap model)
	{
		return Constants.FORM_RENOVAR;
	}

}
