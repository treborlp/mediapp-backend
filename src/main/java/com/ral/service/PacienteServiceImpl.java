package com.ral.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ral.model.Paciente;
import com.ral.repo.IPacienteRepo;

@Service  //Se coloca el sterotipo porque es una clase propia 
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteRepo repo; //Instanciamos el repositorio para el acceso a la base de datos
	
	@Override
	public Paciente registrar(Paciente paciente) throws Exception {
		return repo.save(paciente); //Spring diferencia el uso del metodo save en el create y el update a traves de la llave primaria
									//Cuando un objeto paciente no existe en la BD usa el metodo create en caso contrario usa el metodo update
	}

	@Override
	public Paciente modificar(Paciente paciente) throws Exception {
		return repo.save(paciente);
	}

	@Override
	public List<Paciente> listar() throws Exception {
		return repo.findAll();
	}

	@Override
	public Paciente buscarPorId(Integer idPaciente) throws Exception {
		Optional<Paciente> op = repo.findById(idPaciente);
		return op.isPresent() ? op.get() : new Paciente(); //Operador ternario
	}

	@Override
	public void eliminar(Integer idPaciente) throws Exception {
		repo.deleteById(idPaciente);
		
	}

}
