package com.ral.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Medico;
import com.ral.repo.IMedicoRepo;
import com.ral.service.IMedicoService;

@Service  //Se coloca el sterotipo porque es una clase propia 
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepo repo; //Instanciamos el repositorio para el acceso a la base de datos
	
	@Override
	public Medico registrar(Medico Medico) throws Exception {
		return repo.save(Medico); //Spring diferencia el uso del metodo save en el create y el update a traves de la llave primaria
									//Cuando un objeto Medico no existe en la BD usa el metodo create en caso contrario usa el metodo update
	}

	@Override
	public Medico modificar(Medico Medico) throws Exception {
		return repo.save(Medico);
	}

	@Override
	public List<Medico> listar() throws Exception {
		return repo.findAll();
	}

	@Override
	public Medico buscarPorId(Integer idMedico) throws Exception {
		Optional<Medico> op = repo.findById(idMedico);
		return op.isPresent() ? op.get() : new Medico(); //Operador ternario
	}

	@Override
	public void eliminar(Integer idMedico) throws Exception {
		repo.deleteById(idMedico);
		
	}

}
