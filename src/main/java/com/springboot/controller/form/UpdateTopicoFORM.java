package com.springboot.controller.form;

import javax.validation.constraints.NotEmpty;

import com.springboot.model.Topico;
import com.springboot.repository.TopicoRepository;

public class UpdateTopicoFORM {

	@NotEmpty
	private String titulo;
	@NotEmpty
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Topico update(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id);

		System.out.println(this.titulo);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}

}
