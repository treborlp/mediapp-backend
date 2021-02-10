package com.ral.repo;

import com.ral.model.Consulta;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IConsultaRepo extends IGenericRepo<Consulta, Integer> {
	
}
