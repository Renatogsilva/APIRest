package com.algaworks.oswors.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.oswors.api.domain.model.Cliente;
import com.algaworks.oswors.api.domain.repository.ClienteRepository;
import com.algaworks.oswors.api.domain.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API REST clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@ApiOperation(value = "Retorna uma lista de Cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes encontrados"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Clientes não encontrados"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@ApiOperation(value = "Retorna um Cliente por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente encontrado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Cliente não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra um Cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente cadastrado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@ApiOperation(value = "Atualiza um cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente atualizado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Cliente não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);

		cliente = clienteService.atualizar(cliente);

		return ResponseEntity.ok(cliente);
	}

	@ApiOperation(value = "Deleta um cliente por Id")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Cliente excluído"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Cliente não encontrado"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		clienteService.excluir(clienteId);

		return ResponseEntity.noContent().build();
	}
}
