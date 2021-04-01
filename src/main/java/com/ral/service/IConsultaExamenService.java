package com.ral.service;

import java.util.List;

import com.ral.model.ConsultaExamen;

public interface IConsultaExamenService {
	
	List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta);
}
