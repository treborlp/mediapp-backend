package com.ral.service;

import java.util.List;

import com.ral.model.Paciente;

public interface IPacienteService {
	
	Paciente registrar(Paciente paciente) throws Exception;
	
	Paciente modificar(Paciente paciente) throws Exception;
	
	List<Paciente> listar() throws Exception;
	
	Paciente buscarPorId(Integer idPaciente) throws Exception;
	
	void eliminar(Integer idPaciente) throws Exception;
}
