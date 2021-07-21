package com.serratec.trabalhoAplicado.model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "secretaria")
@SequenceGenerator(name = "generator_secretaria", sequenceName = "sequence_secretaria", initialValue = 1, allocationSize = 1)
public class Paciente extends Usuario{

	private static final long serialVersionUID = -1650680757180742115L;

	public  Paciente(Long id, String email, String username, String senha, String nome, String cpf ,String telefone) {
			super();
	}
		
    public Paciente() {}
}
