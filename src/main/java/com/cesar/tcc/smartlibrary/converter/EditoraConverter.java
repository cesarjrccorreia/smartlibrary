package com.cesar.tcc.smartlibrary.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cesar.tcc.smartlibrary.entity.Editora;
import com.cesar.tcc.smartlibrary.iservice.IEditoraService;

@Component
public class EditoraConverter implements Converter<Object, Editora>
{
	@Autowired
	IEditoraService editoraService;

	@Override
	public Editora convert(final Object element)
	{
		final Integer id = Integer.parseInt((String) element);
		final Editora editora = editoraService.findById(id);

		return editora;
	}

}
