package com.ral.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Examen;
import com.ral.repo.IGenericRepo;
import com.ral.repo.IExamenRepo;
import com.ral.service.IExamenService;

@Service  //Se coloca el sterotipo porque es una clase propia 
public class ExamenServiceImpl extends CRUDImpl<Examen, Integer> implements IExamenService {

	@Autowired
	private IExamenRepo repo; //Instanciamos el repositorio para el acceso a la base de datos

	@Override
	protected IGenericRepo<Examen, Integer> getRepo() {
		return repo;
	}
	


}
