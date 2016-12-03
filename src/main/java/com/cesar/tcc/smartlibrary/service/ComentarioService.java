package com.cesar.tcc.smartlibrary.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesar.tcc.smartlibrary.entity.Comentario;
import com.cesar.tcc.smartlibrary.idao.IComentarioDao;
import com.cesar.tcc.smartlibrary.iservice.IComentarioService;

@Service(value = "comentarioService")
@Transactional
public class ComentarioService implements IComentarioService
{
	@Autowired
	private IComentarioDao comentarioDao;

	@Override
	public void save(final Comentario comentario)
	{
		comentarioDao.save(comentario);
	}

}
