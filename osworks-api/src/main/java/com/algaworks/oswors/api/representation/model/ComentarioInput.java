package com.algaworks.oswors.api.representation.model;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class ComentarioInput {
	
	@ApiModelProperty(value = "Descrição do Comentário", required = true, allowableValues="range[-infinity, 6000]")
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
