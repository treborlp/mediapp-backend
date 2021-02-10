package com.ral.repo;

import com.ral.model.Especialidad;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IEspecialidadRepo extends IGenericRepo<Especialidad, Integer> {
	
}
