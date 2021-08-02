package com.serratec.trabalhoAplicado.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.trabalhoAplicado.model.HistoricoRecibo;

@Repository
public interface HistoricoReciboRepository extends JpaRepository<HistoricoRecibo, Long> {
		
}