package com.ral.repo;

import com.ral.model.Medico;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IMedicoRepo extends IGenericRepo<Medico, Integer> {
	
}
