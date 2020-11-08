package com.algaworks.oswors.api.domain.exception;

public class EntidadeNaoEcontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEcontradaException(String mensagem) {
		super(mensagem);
	}
}
