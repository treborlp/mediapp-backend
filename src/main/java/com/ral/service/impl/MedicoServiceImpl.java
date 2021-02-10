package com.ral.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Medico;
import com.ral.repo.IGenericRepo;
import com.ral.repo.IMedicoRepo;
import com.ral.service.IMedicoService;

@Service  //Se coloca el sterotipo porque es una clase propia 
public class MedicoServiceImpl extends CRUDImpl<Medico, Integer> implements IMedicoService {

	@Autowired
	private IMedicoRepo repo; //Instanciamos el repositorio para el acceso a la base de datos

	@Override
	protected IGenericRepo<Medico, Integer> getRepo() {
		return repo;
	}
	


}
