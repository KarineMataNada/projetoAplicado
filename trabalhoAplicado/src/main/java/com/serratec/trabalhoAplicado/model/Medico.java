package com.serratec.trabalhoAplicado.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_medico", sequenceName = "sequence_medico", initialValue = 1, allocationSize = 1)
public class Medico extends Usuario {
	
		private static final long serialVersionUID = -1650680757180742115L;

		public  Medico(Long id, String email, String username, String senha, String nome, String cpf ,String telefone) {
				super();
		}
		
		@Column(length = 60, unique = true)
		@NotNull
		private String crn;
		
		@Column(length = 60)
		@NotNull
		private String especialidade;
		
		
		@OneToMany(mappedBy = "medico")
		private List<Recibo> recibo;
		
		
	    public String getEspecialidade() {
			return especialidade;
		}
	    
		public void setEspecialidade(String especialidade) {
			this.especialidade = especialidade;
		}
		
		public Medico() {}
		
		public String getCrn() {
			return crn;
		}
		
		public void setCrn(String crn) {
			this.crn = crn;
		}
	}
