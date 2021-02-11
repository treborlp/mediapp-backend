package com.ral.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ral.model.Consulta;
import com.ral.model.Examen;


//No necesita ningun sterotipo
public class ConsultaListaExamenDTO {
	
	@NotNull
	private Consulta consulta;
	private List<Examen> lstExamen;
	
	@NotNull	
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<Examen> getLstExamen() {
		return lstExamen;
	}
	public void setLstExamen(List<Examen> lstExamen) {
		this.lstExamen = lstExamen;
	}
	

}
 