package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.iservice.IEmprestimoService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "emprestimoController")
@RequestMapping(value = "/emprestimo")
public class EmprestimoController extends AppController
{
	@Autowired
	private IEmprestimoService emprestimoService;

	@RequestMapping(value = "/renovar-{username}", method = RequestMethod.GET)
	public String renovar(@PathVariable final String username, final ModelMap model, final RedirectAttributes redirect)
	{
		final List<Emprestimo> emprestimos = emprestimoService.findAllByUser(username);
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s", "");

		if (emprestimos.isEmpty() || emprestimos == null)
		{
			redirect.addFlashAttribute("info", messageSource.getMessage("msg.emprestimo.empty", parameters, locale));
			return redirectMsg;
		}

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		return Constants.FORM_RENOVAR;
	}

}
