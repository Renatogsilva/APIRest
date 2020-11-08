package com.algaworks.oswors.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.oswors.api.domain.model.OrdemServico;
import com.algaworks.oswors.api.domain.repository.OrdemServicoRepository;
import com.algaworks.oswors.api.domain.service.OrdemServicoService;
import com.algaworks.oswors.api.representation.model.OrdemServicoDto;
import com.algaworks.oswors.api.representation.model.OrdemServicoInputDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API REST Ordem de Serviço")
@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServicoService;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Cria ordem de serviço")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Ordem de Serviço cadastrada"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDto criar(@Valid @RequestBody OrdemServicoInputDto ordemServicoInputDto) {

		return convertaOrdemServicoParaOrdemServicoDto(
				ordemServicoService.criar(convertaOrdemServicoInputDtoParaOrdemServico(ordemServicoInputDto)));
	}

	@ApiOperation(value = "Retorna uma lista de Ordens de Serviços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ordens de Serviços encontradas"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Ordens de Serviços não encontradas"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@GetMapping
	public List<OrdemServicoDto> listar() {

		return convertaListaOrdemServicoParaListaOrdemServicoDto(ordemServicoRepository.findAll());
	}

	@ApiOperation(value = "Consulta Ordem de Serviço por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ordem de Serviço encontrada"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Ordem de Serviço não encontrada"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDto> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

		if (ordemServico.isPresent()) {
			return ResponseEntity.ok(convertaOrdemServicoParaOrdemServicoDto(ordemServico.get()));
		}

		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Finaliza Ordem de Serviço Por OrdemServicoId")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Ordem de Serviço finalizada"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Ordem de Serviço não encontrada"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
		ordemServicoService.finalizarOrdemServico(ordemServicoId);
	}

	private OrdemServicoDto convertaOrdemServicoParaOrdemServicoDto(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDto.class);
	}

	private List<OrdemServicoDto> convertaListaOrdemServicoParaListaOrdemServicoDto(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(ordemServico -> convertaOrdemServicoParaOrdemServicoDto(ordemServico))
				.collect(Collectors.toList());
	}

	private OrdemServico convertaOrdemServicoInputDtoParaOrdemServico(OrdemServicoInputDto ordemServicoInputDto) {
		return modelMapper.map(ordemServicoInputDto, OrdemServico.class);
	}
}