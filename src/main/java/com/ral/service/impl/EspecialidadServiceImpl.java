package com.ral.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Especialidad;
import com.ral.repo.IGenericRepo;
import com.ral.repo.IEspecialidadRepo;
import com.ral.service.IEspecialidadService;

@Service  //Se coloca el sterotipo porque es una clase propia 
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad, Integer> implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepo repo; //Instanciamos el repositorio para el acceso a la base de datos

	@Override
	protected IGenericRepo<Especialidad, Integer> getRepo() {
		return repo;
	}
	


}
