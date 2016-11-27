package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.iservice.IDisciplinaService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController extends AppController
{
	@Autowired
	private IDisciplinaService disciplinaService;

	@RequestMapping
	public String listUsers(final ModelMap model)
	{

		final List<Disciplina> disciplinas = disciplinaService.findAll();
		model.addAttribute("disciplinas", disciplinas);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.DISCIPLINA_PAGE;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(final ModelMap model)
	{
		final Disciplina disciplina = new Disciplina();
		model.addAttribute("disciplina", disciplina);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_DISCIPLINA;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid final Disciplina disciplina, final BindingResult result,
			final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_DISCIPLINA;
		}

		if (!disciplinaService.isNameUnique(disciplina.getId(), disciplina.getName()))
		{
			final FieldError nameError = new FieldError("disciplina", "name", messageSource
					.getMessage("non.unique.name", new String[] { disciplina.getName() }, Locale.getDefault()));
			result.addError(nameError);
			return Constants.FORM_DISCIPLINA;
		}

		disciplinaService.persist(disciplina);

		final String[] parameters = new String[] { disciplina.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.DISCIPLINA_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.GET)
	public String edit(@PathVariable final String name, final ModelMap model)
	{
		final Disciplina disciplina = disciplinaService.findByName(name);
		model.addAttribute("disciplina", disciplina);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_DISCIPLINA;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.POST)
	public String update(@Valid final Disciplina disciplina, final BindingResult result,
			final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_DISCIPLINA;
		}

		disciplinaService.update(disciplina);

		final String[] parameters = new String[] { disciplina.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.DISCIPLINA_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/delete-{name}" }, method = RequestMethod.GET)
	public String delete(@PathVariable final String name, final RedirectAttributes redirect)
	{
		final Disciplina disciplina = disciplinaService.findByName(name);
		disciplinaService.delete(disciplina);

		final String[] strings = new String[] { name };
		final Locale locale = Locale.getDefault();

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.deleted", strings, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.DISCIPLINA_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String addDisciplina(final ModelMap model)
	{
		final List<Disciplina> disciplinas = disciplinaService.findAll();
		model.addAttribute("disciplinas", disciplinas);

		return Constants.ADD_DISCIPLINA_PAGE;
	}

}
