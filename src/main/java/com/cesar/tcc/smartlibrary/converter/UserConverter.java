package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.entity.User;
import com.cesar.tcc.smartlibrary.iservice.IUserService;

@Component
public class UserConverter implements Converter<Object, User>
{
	@Autowired
	private IUserService userService;

	@Override
	public User convert(final Object element)
	{
		if (element instanceof User)
		{
			return (User) element;
		}

		final Integer id = Integer.parseInt((String) element);
		final User user = userService.findById(id);

		return user;
	}

}
