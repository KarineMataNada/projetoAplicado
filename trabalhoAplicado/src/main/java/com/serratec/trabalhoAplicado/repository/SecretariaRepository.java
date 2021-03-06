package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Secretaria;


@Repository
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
		
	Optional<Secretaria> findById(Long id);
	Optional<Secretaria> findByUsername(String username);
	List<Secretaria> findAllByNomeContainingIgnoreCase(String nome);

}