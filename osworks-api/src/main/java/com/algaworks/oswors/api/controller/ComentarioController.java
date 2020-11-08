package com.algaworks.oswors.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.oswors.api.domain.exception.EntidadeNaoEcontradaException;
import com.algaworks.oswors.api.domain.model.Comentario;
import com.algaworks.oswors.api.domain.model.OrdemServico;
import com.algaworks.oswors.api.domain.repository.OrdemServicoRepository;
import com.algaworks.oswors.api.domain.service.OrdemServicoService;
import com.algaworks.oswors.api.representation.model.ComentarioDto;
import com.algaworks.oswors.api.representation.model.ComentarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API REST Comentário")
@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private OrdemServicoService ordemServicoService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Cria um comentário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Comentário cadastrado"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ComentarioDto adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = ordemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

		return converteComentarioParaComentarioDto(comentario);
	}

	@ApiOperation(value = "Retorna Lista de Comentários Por Ordem de Servico")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Comentários encontrados"),
			@ApiResponse(code = 401, message = "Não autorizado"),
			@ApiResponse(code = 400, message = "Má conexção com o Servidor"),
			@ApiResponse(code = 404, message = "Comentários não encontrados"),
			@ApiResponse(code = 500, message = "Erro interno de Servidor") })
	@GetMapping
	public List<ComentarioDto> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEcontradaException("Ordem de serviço não encontrada."));

		return convertaListaComentarioParaListaComentarioDto(ordemServico.getComentarios());
	}

	private List<ComentarioDto> convertaListaComentarioParaListaComentarioDto(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> converteComentarioParaComentarioDto(comentario))
				.collect(Collectors.toList());
	}

	private ComentarioDto converteComentarioParaComentarioDto(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDto.class);
	}
}