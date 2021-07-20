package com.serratec.trabalhoAplicado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.trabalhoAplicado.model.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	Optional<Endereco> findById(Long id);
}
