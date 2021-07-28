package com.springboot.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.controller.dto.DetailTopicoDTO;
import com.springboot.controller.dto.TopicoDTO;
import com.springboot.controller.form.TopicoFORM;
import com.springboot.controller.form.UpdateTopicoFORM;
import com.springboot.model.Topico;
import com.springboot.repository.CursoRepository;
import com.springboot.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDTO> index(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDTO.converter(topicos);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailTopicoDTO> show(@PathVariable Long id) {

		try {
			Topico topico = topicoRepository.getById(id);
			return ResponseEntity.ok(new DetailTopicoDTO(topico));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	public ResponseEntity<TopicoDTO> store(@RequestBody @Valid TopicoFORM form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicoFORM form) {

		try {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> destroy(@PathVariable Long id) {

		try {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
