package com.cesar.tcc.smartlibrary.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cesar.tcc.smartlibrary.iservice.AuthorService;
import com.cesar.tcc.smartlibrary.iservice.UserProfileService;
import com.cesar.tcc.smartlibrary.model.Author;

@Controller(value = "authorController")
@RequestMapping(value = "/authors")
public class AuthorController extends AppController {

	@Autowired
	AuthorService authorService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String listAuthor(final ModelMap model) {

		final List<Author> authors = authorService.findAll();
		model.addAttribute("authors", authors);
		model.addAttribute("loggedinuser", getPrincipal());

		return "authorlist";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid final Author author, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "formUser";
		}

		if (!authorService.isNameUnique(author.getId(), author.getName())) {
			final FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { author.getName() }, Locale.getDefault()));
			result.addError(usernameError);
			return "formUser";
		}

		authorService.save(author);

		model.addAttribute("success", "User " + author.getName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "formUsersuccess";
	}

}
