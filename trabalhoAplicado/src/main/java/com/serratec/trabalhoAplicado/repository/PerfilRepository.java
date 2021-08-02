package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.Perfil;


@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
		
	Optional<Perfil> findById(Long id);
	List<Perfil> findAllByNomeContainingIgnoreCase(String nome);

}