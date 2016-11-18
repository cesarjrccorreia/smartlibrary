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

import com.cesar.tcc.smartlibrary.entity.Author;
import com.cesar.tcc.smartlibrary.iservice.AuthorService;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller(value = "authorController")
@RequestMapping(value = "/authors")
public class AuthorController extends AppController
{

	@Autowired
	AuthorService authorService;

	@RequestMapping
	public String listAuthor(final ModelMap model)
	{

		final List<Author> authors = authorService.findAll();
		model.addAttribute("authors", authors);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.AUTHOR_PAGE;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAuthor(final ModelMap model)
	{

		final Author author = new Author();
		model.addAttribute("author", author);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_AUTHOR;
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveAuthor(@Valid final Author author, final BindingResult result, final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_AUTHOR;
		}

		final Locale locale = Locale.getDefault();
		if (!authorService.isNameUnique(author.getId(), author.getName()))
		{
			final FieldError usernameError = new FieldError("user", "username",
					messageSource.getMessage("non.unique.username", new String[] { author.getName() }, locale));
			result.addError(usernameError);

			return Constants.FORM_AUTHOR;
		}

		authorService.save(author);
		final String[] arguments = new String[] {};

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.saved", arguments, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.AUTHOR_PAGE);
		return redirectMsg;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.GET)
	public String editAuthor(@PathVariable final String name, final ModelMap model)
	{
		final Author author = authorService.findByName(name);
		model.addAttribute("author", author);
		model.addAttribute("edit", true);

		return Constants.FORM_AUTHOR;
	}

	@RequestMapping(value = { "/edit-{name}" }, method = RequestMethod.POST)
	public String updateAuthor(@Valid final Author author, final BindingResult result,
			final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_AUTHOR;
		}

		authorService.updateAuthor(author);

		final String[] parameters = new String[] { author.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.updated", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.AUTHOR_PAGE);
		return redirectMsg;
	}

	@RequestMapping(value = { "/delete-{name}" }, method = RequestMethod.GET)
	public String deleteAuthor(@PathVariable final String name, final RedirectAttributes redirect)
	{

		authorService.deleteByName(name);

		final Locale locale = Locale.getDefault();

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.deleted", null, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.AUTHOR_PAGE);
		return redirectMsg;
	}

}
