package com.ral.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ral.dto.ConsultaListaExamenDTO;
import com.ral.dto.FiltroConsultaDTO;
import com.ral.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer>{
	
	Consulta registrarTransaccional(Consulta consulta);
	
	Consulta registrarTransaccionalDTO(ConsultaListaExamenDTO consulta);
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	List<Consulta> buscarFecha(LocalDateTime fecha);
	
}
