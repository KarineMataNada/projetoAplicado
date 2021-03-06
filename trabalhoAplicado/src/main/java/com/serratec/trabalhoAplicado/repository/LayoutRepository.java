package com.serratec.trabalhoAplicado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.serratec.trabalhoAplicado.model.Layout;


@Repository
public interface LayoutRepository extends JpaRepository<Layout, Long> {
		
	Optional<Layout> findById(Long id);
	List<Layout> findAllByNomeContainingIgnoreCase(String nome);

}