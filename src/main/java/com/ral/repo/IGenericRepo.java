package com.ral.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //Le indica a spring que esta interfaz no necesita una instancia en PoC
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID> {

}
