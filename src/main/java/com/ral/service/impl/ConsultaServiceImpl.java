package com.ral.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Consulta;
import com.ral.repo.IGenericRepo;
import com.ral.repo.IConsultaRepo;
import com.ral.service.IConsultaService;

@Service // Se coloca el sterotipo porque es una clase propia
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService {

	@Autowired
	private IConsultaRepo repo; // Instanciamos el repositorio para el acceso a la base de datos

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}

}
