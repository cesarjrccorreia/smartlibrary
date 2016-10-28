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

import com.cesar.tcc.smartlibrary.iservice.UserService;
import com.cesar.tcc.smartlibrary.model.User;

@Controller
@RequestMapping("/")
public class UserController extends AppController {

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(final ModelMap model) {

		final List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());

		return "userslist";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid final User user, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
			final FieldError usernameError = new FieldError("user", "username", messageSource
					.getMessage("non.unique.username", new String[] { user.getUsername() }, Locale.getDefault()));
			result.addError(usernameError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		// return "success";
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable final String username, final ModelMap model) {
		final User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	@RequestMapping(value = { "/edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid final User user, final BindingResult result, final ModelMap model,
			@PathVariable final String username) {

		if (result.hasErrors()) {
			return "registration";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable final String username) {
		userService.deleteUserByUsername(username);
		return "redirect:/list";
	}

}
