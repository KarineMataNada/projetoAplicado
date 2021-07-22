package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Medico;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Optional<Medico> findById(Long id);
	Optional<Medico> findByUsername(String username);
	List<Medico> findAllByNomeContainingIgnoreCase(String nome);
}


