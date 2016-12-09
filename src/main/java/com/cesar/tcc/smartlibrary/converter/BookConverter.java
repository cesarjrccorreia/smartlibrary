package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.entity.Book;
import com.cesar.tcc.smartlibrary.iservice.IBookService;

@Component
public class BookConverter implements Converter<Object, Book>
{
	@Autowired
	private IBookService bookService;

	@Override
	public Book convert(final Object element)
	{
		if (element instanceof Book)
		{
			return (Book) element;
		}

		final Integer id = Integer.parseInt((String) element);
		final Book book = bookService.findById(id);

		return book;
	}

}
