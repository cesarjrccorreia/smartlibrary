package com.cesar.tcc.smartlibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

}
