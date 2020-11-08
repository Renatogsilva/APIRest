package com.algaworks.oswors.api.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.oswors.api.domain.exception.EntidadeNaoEcontradaException;
import com.algaworks.oswors.api.domain.exception.NegocioException;
import com.algaworks.oswors.api.domain.model.Cliente;
import com.algaworks.oswors.api.domain.model.Comentario;
import com.algaworks.oswors.api.domain.model.OrdemServico;
import com.algaworks.oswors.api.domain.model.StatusOrdemServico;
import com.algaworks.oswors.api.domain.repository.ClienteRepository;
import com.algaworks.oswors.api.domain.repository.ComentarioRepository;
import com.algaworks.oswors.api.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));

		ordemServico.setCliente(cliente);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		ordemServico.setStatus(StatusOrdemServico.ABERTA);

		return ordemServicoRepository.save(ordemServico);
	}

	public void finalizarOrdemServico(Long ordemServicoId) {
		OrdemServico ordemServico = consultaOrdemServicoPorId(ordemServicoId);

		ordemServico.finalizar();

		ordemServicoRepository.save(ordemServico);
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = consultaOrdemServicoPorId(ordemServicoId);

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepository.save(comentario);
	}

	private OrdemServico consultaOrdemServicoPorId(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEcontradaException("Ordem de serviço não encontrada."));
	}
}
