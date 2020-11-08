package com.algaworks.oswors.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.oswors.api.domain.exception.NegocioException;
import com.algaworks.oswors.api.domain.model.Cliente;
import com.algaworks.oswors.api.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

		if (clienteExistente != null && !clienteExistente.equals(cliente))
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
