package com.algaworks.oswors.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.oswors.api.domain.exception.EntidadeNaoEcontradaException;
import com.algaworks.oswors.api.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntidadeNaoEcontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEcontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;

		Problema problema = retornaProblema(status, OffsetDateTime.now(), ex.getMessage(), null);

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		Problema problema = retornaProblema(status, OffsetDateTime.now(), ex.getMessage(), null);

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		List<Campo> listaCampos = new ArrayList<Campo>();
		String titulo = "Um ou mais campos estão inválidos."
				+ "Por favor verificar se existem campos não preenchidos ou preenchidos fora do padrão aceitável.";

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nomeCampo = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			listaCampos.add(new Campo(nomeCampo, mensagem));
		}

		Problema problema = retornaProblema(status, OffsetDateTime.now(), titulo, listaCampos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private Problema retornaProblema(HttpStatus status, OffsetDateTime data, String titulo, List<Campo> listaCampos) {
		Problema problema = new Problema();

		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTitulo(titulo);
		problema.setCampos(listaCampos);

		return problema;
	}
}