package com.ral.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ral.dto.ConsultaListaExamenDTO;
import com.ral.dto.FiltroConsultaDTO;
import com.ral.model.Consulta;
import com.ral.repo.IConsultaExamenRepo;
import com.ral.repo.IConsultaRepo;
import com.ral.repo.IGenericRepo;
import com.ral.service.IConsultaService;

@Service // Se coloca el sterotipo porque es una clase propia
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService {

	@Autowired
	private IConsultaRepo repo; // Instanciamos el repositorio para el acceso a la base de datos
	
	@Autowired
	private IConsultaExamenRepo ceRepo;

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}

	@Override
	public Consulta registrarTransaccional(Consulta consulta) {
		consulta.getDetalleConsulta().forEach(det -> det.setConsulta(consulta));
		return repo.save(consulta);
	}
	
	@Transactional
	@Override
	public Consulta registrarTransaccionalDTO(ConsultaListaExamenDTO dto) {
		
		dto.getConsulta().getDetalleConsulta().forEach(det -> det.setConsulta(dto.getConsulta()));
		repo.save(dto.getConsulta());
		dto.getLstExamen().forEach(ex -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), ex.getIdExamen()));
		
		return dto.getConsulta();
		
	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return repo.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(LocalDateTime fecha) {
		return repo.buscarFechas(fecha, fecha.plusDays(1));
	}

}
