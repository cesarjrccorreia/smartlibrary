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

import com.cesar.tcc.smartlibrary.entity.Editora;
import com.cesar.tcc.smartlibrary.iservice.EditoraService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "editoraController")
@RequestMapping(value = "editoras")
public class EditoraController extends AppController
{

	@Autowired
	private EditoraService editoraService;

	@RequestMapping
	public String listAll(final ModelMap model)
	{

		final List<Editora> editoras = editoraService.findAll();
		model.addAttribute("editoras", editoras);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.EDITORA_PAGE;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEditora(final ModelMap model)
	{

		final Editora editora = new Editora();
		model.addAttribute("editora", editora);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_EDITORA;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid final Editora editora, final BindingResult result, final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_EDITORA;
		}

		final Locale locale = Locale.getDefault();
		final boolean isNameUnique = editoraService.isNameUnique(editora.getId(), editora.getName());

		if (!isNameUnique)
		{
			final FieldError usernameError = new FieldError("user", "username",
					messageSource.getMessage("non.unique.username", new String[] { editora.getName() }, locale));
			result.addError(usernameError);

			return Constants.FORM_EDITORA;
		}

		editoraService.save(editora);
		final String[] arguments = new String[] {};

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", arguments, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.EDITORA_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.GET)
	public String edit(@PathVariable final String name, final ModelMap model)
	{
		final Editora editora = editoraService.findByName(name);
		model.addAttribute("editora", editora);
		model.addAttribute("edit", true);

		return Constants.FORM_EDITORA;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.POST)
	public String update(@Valid final Editora editora, final BindingResult result, final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_EDITORA;
		}

		editoraService.update(editora);

		final String[] parameters = new String[] { editora.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.EDITORA_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/delete-{name}" }, method = RequestMethod.GET)
	public String deleteAuthor(@PathVariable final String name, final RedirectAttributes redirect)
	{

		editoraService.deleteByName(name);

		final Locale locale = Locale.getDefault();

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.deleted", null, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.EDITORA_PAGE);

		return redirectMsg;
	}

}
