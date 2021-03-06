package com.serratec.trabalhoAplicado.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@Entity
@Table(name = "usuario")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "generator_usuario", sequenceName = "sequence_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements UserDetails {


	private static final long serialVersionUID = 8415777820259328596L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuario")
	private Long id;
	
	@Column(length = 255, unique = true)
	@NotNull
	private String email; 
	
	@Column(length = 60, unique = true)
	@NotNull
	private String username;
	
    @Column(length = 255)
    @NotNull
	private String senha;
	
	@Column(length = 60)
	@NotNull
	private String nome;
	
	@Column(length = 11, unique = true)
	@NotNull
	private String cpf;
	
	@Column(length = 60)
	@NotNull
	private String telefone;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
	@NotNull
	private Endereco endereco;
	
	private LocalDate dataNascimento;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

	public Usuario() {}
	

	public Usuario(Long id, @NotNull String email, @NotNull String username, @NotNull String senha,
			@NotNull String nome, @NotNull String cpf, @NotNull String telefone, @NotNull Endereco endereco,
			LocalDate dataNascimento, List<Perfil> perfis) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.perfis = perfis;
	}


	
	public List<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getSenha() {
		return senha;
	}
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}


	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}


	
	
}
