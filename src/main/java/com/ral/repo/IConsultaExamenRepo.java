package com.ral.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ral.model.ConsultaExamen;

public interface IConsultaExamenRepo extends IGenericRepo<ConsultaExamen, Integer> {
	
	//@Transactional // Trabaja siempre con Modifying
	@Modifying //LE DICE QUE LA INSTRUCCION SERA DE TIPO UPDATE, INSERT DELETE
	@Query(value="INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery= true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);

}
