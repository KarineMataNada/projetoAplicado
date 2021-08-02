package com.serratec.trabalhoAplicado.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
		
	Optional<Empresa> findById(Long id);


}