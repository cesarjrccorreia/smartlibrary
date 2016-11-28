package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.entity.Disciplina;
import com.cesar.tcc.smartlibrary.iservice.IDisciplinaService;

@Component
public class DisciplinaConverter implements Converter<Object, Disciplina>
{
	@Autowired
	IDisciplinaService disciplinaService;

	@Override
	public Disciplina convert(final Object element)
	{
		final Integer id = Integer.parseInt((String) element);
		final Disciplina disciplina = disciplinaService.getByKey(id);

		return disciplina;
	}

}
