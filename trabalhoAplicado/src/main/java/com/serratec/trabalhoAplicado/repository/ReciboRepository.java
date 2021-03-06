package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Recibo;


@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Long> {
		
	Optional<Recibo> findById(Long id);
	Optional<List<Recibo>> findAllByStatusFinalizadoTrue();
	
}