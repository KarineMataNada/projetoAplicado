package com.serratec.trabalhoAplicado.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_secretaria", sequenceName = "sequence_secretaria", initialValue = 1, allocationSize = 1)
public class Secretaria extends Usuario{

	private static final long serialVersionUID = 4075537874778880771L;

	public  Secretaria(Long id, String email, String username, String senha, String nome, String cpf ,String telefone) {
			super();
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "secretaria")
	private List<Recibo> recibo;
	
    public Secretaria() {}


    
}

