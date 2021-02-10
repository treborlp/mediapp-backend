package com.ral.repo;

import com.ral.model.Examen;

//Cuando heredamos de JpaRepository ya no es necesario sterotipar (@Repository) la interfaz. Spring automaticamente lo reconoce
public interface IExamenRepo extends IGenericRepo<Examen, Integer> {
	
}
