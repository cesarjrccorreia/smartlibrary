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

import com.cesar.tcc.smartlibrary.iservice.UserService;
import com.cesar.tcc.smartlibrary.model.User;
import com.cesar.tcc.smartlibrary.utilities.Constants;

@Controller
@RequestMapping("/users")
public class UserController extends AppController
{

	@Autowired
	UserService userService;

	@RequestMapping()
	public String listUsers(final ModelMap model)
	{

		final List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.USER_PAGE;
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(final ModelMap model)
	{
		final User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_USER;
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid final User user, final BindingResult result, final RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_USER;
		}

		if (!userService.isUsernameUnique(user.getId(), user.getUsername()))
		{
			final FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			return Constants.FORM_USER;
		}

		userService.saveUser(user);

		final String[] parameters = new String[] { user.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.user.saved", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.USER_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable final String username, final ModelMap model)
	{
		final User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());

		return Constants.FORM_USER;
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid final User user, final BindingResult result, final RedirectAttributes redirect,
			@PathVariable final String username)
	{

		if (result.hasErrors())
		{
			return Constants.FORM_USER;
		}

		userService.updateUser(user);

		final String[] parameters = new String[] { user.getName() };
		final Locale locale = Locale.getDefault();
		redirect.addFlashAttribute("success", messageSource.getMessage("msg.user.updated", parameters, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.USER_PAGE);

		return redirectMsg;
	}

	@RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable final String username, final RedirectAttributes redirect)
	{
		userService.deleteUserByUsername(username);

		final String[] strings = new String[] { username };
		final Locale locale = Locale.getDefault();

		redirect.addFlashAttribute("success", messageSource.getMessage("msg.delete.success", strings, locale));

		final String redirectMsg = String.format("redirect:/%s", Constants.USER_PAGE);

		return redirectMsg;
	}

}
