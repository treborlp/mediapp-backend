package com.ral.repo;

import com.ral.model.Paciente;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IPacienteRepo extends IGenericRepo<Paciente, Integer> {
	
}
