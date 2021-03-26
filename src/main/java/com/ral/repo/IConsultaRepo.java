package com.ral.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ral.model.Consulta;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IConsultaRepo extends IGenericRepo<Consulta, Integer> {
	
	@Query("FROM Consulta c WHERE c.paciente.dni = :dni OR LOWER(c.paciente.nombres) LIKE %:nombreCompleto% OR LOWER(c.paciente.apellidos) LIKE %:nombreCompleto%")
	List<Consulta> buscar(@Param("dni") String dni, @Param("nombreCompleto") String nombreCompleto);
	
	@Query("FROM Consulta c WHERE c.fecha BETWEEN :fechaConsulta AND :fechaSgte")
	List<Consulta> buscarFechas(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSig);
	
}
	