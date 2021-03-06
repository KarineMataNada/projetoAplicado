package com.serratec.trabalhoAplicado.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.serratec.trabalhoAplicado.model.Procedimentos;

@Repository
public interface ProcedimentosRepository extends JpaRepository<Procedimentos, Long> {
		
	Optional<Procedimentos> findById(Long id);
	List<Procedimentos> findAllByNomeProcedimentoContainingIgnoreCase(String nomeProcedimento);

}