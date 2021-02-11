package com.ral.service;

import com.ral.dto.ConsultaListaExamenDTO;
import com.ral.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer>{
	
	Consulta registrarTransaccional(Consulta consulta);
	
	Consulta registrarTransaccionalDTO(ConsultaListaExamenDTO consulta);
	
}
