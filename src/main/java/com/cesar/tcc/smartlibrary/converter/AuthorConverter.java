package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.entity.Author;
import com.cesar.tcc.smartlibrary.iservice.IAuthorService;

@Component
public class AuthorConverter implements Converter<Object, Author>
{
	@Autowired
	IAuthorService authorService;

	@Override
	public Author convert(final Object element)
	{
		final Integer id = Integer.parseInt((String) element);
		final Author author = authorService.findById(id);

		return author;
	}

}
