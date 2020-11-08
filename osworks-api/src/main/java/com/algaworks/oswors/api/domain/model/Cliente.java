package com.algaworks.oswors.api.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.oswors.api.domain.ValidationGroups;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Código do Cliente")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = ValidationGroups.ClienteId.class)
	private Long id;

	@ApiModelProperty(value = "Nome do Cliente", required = true, allowableValues="range[-infinity, 100]")
	@Max(100)
	@NotBlank
	@Size(max = 100)
	@Column(name = "Nome")
	private String nome;

	@ApiModelProperty(value = "E-mail do Cliente", required = true, allowableValues="range[-infinity, 200]")
	@Email
	@NotBlank
	@Size(max = 200)
	@Column(name = "Email")
	private String email;

	@ApiModelProperty(value = "Telefone do Cliente", required = true, allowableValues="range[-infinity, 20]")
	@NotBlank
	@Size(max = 20)
	@Column(name = "Fone")
	private String telefone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
