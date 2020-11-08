package com.algaworks.oswors.api.representation.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.oswors.api.domain.model.OrdemServico;
import com.algaworks.oswors.api.domain.model.StatusOrdemServico;

public class OrdemServicoDto {
	public OrdemServicoDto() {
	}

	public OrdemServicoDto(OrdemServico ordemServico) {
		this.id = ordemServico.getId();
		this.cliente.setId(ordemServico.getCliente().getId());
		this.cliente.setNome(ordemServico.getCliente().getNome());
		this.cliente.setEmail(ordemServico.getCliente().getEmail());
		this.cliente.setTelefone(ordemServico.getCliente().getTelefone());
		this.descricaoOrdemServico = ordemServico.getDescricao();
		this.PrecoOrdemServico = ordemServico.getPreco();
		this.statusOrdemServico = ordemServico.getStatus();
		this.dataAberturaOrdemServico = ordemServico.getDataAbertura();
		this.dataFechamentoOrdemServico = ordemServico.getDataFinalizacao();
	}

	private long id;
	private ClienteResumoDto cliente;
	private String descricaoOrdemServico;
	private BigDecimal PrecoOrdemServico;
	private StatusOrdemServico statusOrdemServico;
	private OffsetDateTime dataAberturaOrdemServico;
	private OffsetDateTime dataFechamentoOrdemServico;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClienteResumoDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoDto cliente) {
		this.cliente = cliente;
	}

	public String getDescricaoOrdemServico() {
		return descricaoOrdemServico;
	}

	public void setDescricaoOrdemServico(String descricaoOrdemServico) {
		this.descricaoOrdemServico = descricaoOrdemServico;
	}

	public BigDecimal getPrecoOrdemServico() {
		return PrecoOrdemServico;
	}

	public void setPrecoOrdemServico(BigDecimal precoOrdemServico) {
		PrecoOrdemServico = precoOrdemServico;
	}

	public StatusOrdemServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}

	public OffsetDateTime getDataAberturaOrdemServico() {
		return dataAberturaOrdemServico;
	}

	public void setDataAberturaOrdemServico(OffsetDateTime dataAberturaOrdemServico) {
		this.dataAberturaOrdemServico = dataAberturaOrdemServico;
	}

	public OffsetDateTime getDataFechamentoOrdemServico() {
		return dataFechamentoOrdemServico;
	}

	public void setDataFechamentoOrdemServico(OffsetDateTime dataFechamentoOrdemServico) {
		this.dataFechamentoOrdemServico = dataFechamentoOrdemServico;
	}
}
