package com.algaworks.oswors.api.representation.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ClienteIdInputDto {

	@ApiModelProperty(value = "Código do Cliente", required = true)
	@NotNull
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
