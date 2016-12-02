package com.cesar.tcc.smartlibrary.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.iservice.IBookService;
import com.cesar.tcc.smartlibrary.iservice.IEmprestimoService;
import com.cesar.tcc.smartlibrary.iservice.IUserService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "emprestimoController")
@RequestMapping(value = "/emprestimo")
public class EmprestimoController extends AppController
{
	@Autowired
	private IEmprestimoService emprestimoService;

	@Autowired
	private IBookService bookService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newEmprestimo(final ModelMap model)
	{
		final Emprestimo emprestimo = new Emprestimo();
		model.addAttribute("emprestimo", emprestimo);

		return Constants.FORM_EMPRESTIMO;
	}

	@ModelAttribute(value = "books")
	public List<Book> listAllBooks()
	{
		final List<Book> books = bookService.findAll();

		return books;
	}

	@ModelAttribute(value = "users")
	public List<User> listAllUser()
	{
		final List<User> users = userService.findAllUsers();

		return users;
	}

	@RequestMapping(value = "/close")
	public String showFormCloseEmprestimo(final ModelMap model)
	{

		return Constants.FORM_CLOSE_EMPRESTIMO;
	}

	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public String closeEmprestimo(final ModelMap model, @RequestParam("search") final String username)
	{
		final List<Emprestimo> emprestimos = emprestimoService.findAllByUser(username);
		model.addAttribute("emprestimos", emprestimos);

		return Constants.FORM_CLOSE_EMPRESTIMO;
	}

	@RequestMapping(value = "/finalizar-{id}")
	public String finalizar(@PathVariable final Integer id, final RedirectAttributes redirect)
	{
		final Emprestimo emprestimo = emprestimoService.findById(id);
		final Date fimEmprestimo = new Date();
		emprestimo.setFim(fimEmprestimo);

		emprestimoService.update(emprestimo);

		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s", Constants.CLOSE_EMPRESTIMO);

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		return redirectMsg;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveEmprestimo(@Valid final Emprestimo emprestimo, final BindingResult result,
			final RedirectAttributes redirect)
	{
		final Date inicio = new Date();
		emprestimo.setInicio(inicio);

		if (result.hasErrors())
		{
			return Constants.FORM_EMPRESTIMO;
		}

		emprestimoService.persist(emprestimo);

		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s", "");

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", parameters, locale));

		return redirectMsg;
	}

	@RequestMapping(value = "/gorenovar-{username}", method = RequestMethod.GET)
	public String goRenovar(@PathVariable final String username, final ModelMap model,
			final RedirectAttributes redirect)
	{
		final List<Emprestimo> emprestimos = emprestimoService.findAllByUser(username);
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s", "");

		if (emprestimos.isEmpty() || emprestimos == null)
		{
			redirect.addFlashAttribute("info",
					messageSource.getMessage("msg.emprestimo.empty.renovar", parameters, locale));
			return redirectMsg;
		}

		model.addAttribute("emprestimos", emprestimos);
		model.addAttribute("renovar", true);

		return Constants.FORM_CLOSE_EMPRESTIMO;
	}

	@RequestMapping(value = "/renovar-{id}", method = RequestMethod.GET)
	public String renovar(@PathVariable final Integer id, final RedirectAttributes redirect)
	{
		final Emprestimo emprestimo = emprestimoService.findById(id);
		final String[] parameters = new String[] {};
		final Locale locale = Locale.getDefault();
		final String redirectMsg = String.format("redirect:/%s-%s", Constants.RENOVAR, getPrincipal());

		if (emprestimo.isLate())
		{
			redirect.addFlashAttribute("error", messageSource.getMessage("msg.emprestimo.late", parameters, locale));
			return redirectMsg;
		}

		final Date newInicio = new Date();
		emprestimo.setInicio(newInicio);

		emprestimoService.update(emprestimo);

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		return redirectMsg;
	}

}
