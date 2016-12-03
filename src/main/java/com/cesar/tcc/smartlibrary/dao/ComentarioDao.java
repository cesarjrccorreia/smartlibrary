package com.cesar.tcc.smartlibrary.dao;

import org.springframework.stereotype.Repository;

import com.cesar.tcc.smartlibrary.entity.Comentario;
import com.cesar.tcc.smartlibrary.idao.IComentarioDao;

@Repository(value = "comentarioDao")
public class ComentarioDao extends AbstractDao<Integer, Comentario> implements IComentarioDao
{

	@Override
	public void save(final Comentario comentario)
	{
		persist(comentario);
	}

}
