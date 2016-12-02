package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.entity.Emprestimo;
import com.cesar.tcc.smartlibrary.entity.Informe;
import com.cesar.tcc.smartlibrary.entity.Reserva;
import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.iservice.IBookService;
import com.cesar.tcc.smartlibrary.iservice.IEmprestimoService;
import com.cesar.tcc.smartlibrary.iservice.IInformeService;
import com.cesar.tcc.smartlibrary.iservice.IReservaService;
import com.cesar.tcc.smartlibrary.iservice.IUserService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "mainController")
@RequestMapping(value = "/")
public class MainController extends AppController
{
	@Autowired
	private IInformeService informeService;

	@Autowired
	private IBookService bookService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IEmprestimoService emprestimoService;

	@Autowired
	private IReservaService reservaService;

	@RequestMapping
	public String startMainPage(final ModelMap modelMap)
	{
		final String username = (String) getPrincipal();
		modelMap.addAttribute("loggedinuser", username);

		final User user = userService.findByUsername(username);
		modelMap.addAttribute("disciplinas", user.getDisciplinas());

		final List<Emprestimo> emprestimos = emprestimoService.findAllByUser(username);
		modelMap.addAttribute("emprestimos", emprestimos);

		final List<Reserva> reservas = reservaService.findByUser(username);
		modelMap.addAttribute("reservas", reservas);

		final List<Informe> informes = informeService.findAll();
		modelMap.addAttribute("informes", informes);

		final List<Book> livros = bookService.recommender();
		modelMap.addAttribute("books", livros);

		return Constants.MAIN_PAGE;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String searchBook(final ModelMap modelMap, @RequestParam final String search)
	{
		final List<Book> book = bookService.findAll();

		modelMap.addAttribute("books", book);
		modelMap.addAttribute("search", search);

		return Constants.SEARCH_PAGE;
	}

	@RequestMapping(value = "/informe/new")
	public String addInforme(final ModelMap modelMap)
	{
		final Informe informe = new Informe();
		modelMap.addAttribute("informe", informe);
		return Constants.FORM_INFORME;
	}

	@RequestMapping(value = "/informe/new", method = RequestMethod.POST)
	public String saveInforme(@Valid final Informe informe, final BindingResult result,
			final RedirectAttributes redirect)
	{
		if (result.hasErrors())
		{
			return Constants.FORM_INFORME;
		}

		final String userName = (String) getPrincipal();
		final User user = userService.findByUsername(userName);
		informe.setUser(user);
		informeService.persist(informe);

		final String[] parameters = new String[] { "informe" };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", parameters, locale));

		final String redirectMsg = String.format("redirect:%s", "/");

		return redirectMsg;
	}

}
