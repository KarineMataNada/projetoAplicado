package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Paciente;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
		
	Optional<Paciente> findById(Long id);
	Optional<Paciente> findByUsername(String username);
	List<Paciente> findAllByNomeContainingIgnoreCase(String nome);

}