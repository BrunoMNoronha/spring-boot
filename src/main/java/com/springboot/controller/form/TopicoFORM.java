package com.springboot.controller.form;

import javax.validation.constraints.NotEmpty;

import com.springboot.model.Curso;
import com.springboot.model.Topico;
import com.springboot.repository.CursoRepository;

public class TopicoFORM {

	@NotEmpty
	private String titulo;
	@NotEmpty
	private String mensagem;
	@NotEmpty
	private String nomeCurso;

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

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}

}
